package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.League;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.repositories.UserLeagueMappingRepository;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SubLeaguesService {

    private GameService gameService;
    private UserLeagueMappingRepository userLeagueMappingRepository;

    @Autowired
    SubLeaguesService(GameService gameService, UserLeagueMappingRepository userLeagueMappingRepository) {
        this.gameService = gameService;
        this.userLeagueMappingRepository = userLeagueMappingRepository;
    }


    public List<List<LeaderBoardEntryViewModel>> calculateSubLeagues(User user) {
        Iterable<League> subLeagues = userLeagueMappingRepository.findAllLeaguesByUserId(user.getId());
        List<List<LeaderBoardEntryViewModel>> subLeagueTables = new ArrayList<>();
        for (League subLeague : subLeagues) {
            subLeagueTables.add(calculateSubLeague(subLeague));
        }
        return subLeagueTables;
    }

    private List<LeaderBoardEntryViewModel> calculateSubLeague(League subLeague) {
        HashMap<Long, LeaderBoardEntryViewModel> subLeagueTable = new HashMap<>();
        Iterable<Game> games = gameService.getGames();
        for (User user : userLeagueMappingRepository.findAllUsersByLeagueId(subLeague.getId())) {
            LeaderBoardEntryViewModel entry = new LeaderBoardEntryViewModel(user.getNickname());
            subLeagueTable.put(user.getId(), entry);
        }

        for (Game game : games) {
            Long winnerId = game.getWinnerId();
            Long loserId = game.getLoserId();

            if (subLeagueTable.containsKey(winnerId) && subLeagueTable.containsKey(loserId)) {
                LeaderBoardEntryViewModel winnerEntry = subLeagueTable.get(winnerId);
                winnerEntry.addWin();
                LeaderBoardEntryViewModel loserEntry = subLeagueTable.get(loserId);
                loserEntry.addLoss();
            }
        }
        return generateSortedTable(subLeagueTable);

    }

    private List<LeaderBoardEntryViewModel> generateSortedTable(HashMap<Long, LeaderBoardEntryViewModel> subLeagueTable) {
        List<LeaderBoardEntryViewModel> subLeagueEntries = new ArrayList<LeaderBoardEntryViewModel>(subLeagueTable.values());

        Comparator<LeaderBoardEntryViewModel> leaderBoardComparator = Comparator
                .comparing((LeaderBoardEntryViewModel e) -> e.getPercentage())
                .thenComparing(e -> e.getWins());

        Collections.sort(subLeagueEntries, leaderBoardComparator.reversed());

        int position = 1;
        for (LeaderBoardEntryViewModel entry : subLeagueEntries) {
            entry.setPosition(position);
            position++;
        }

        return subLeagueEntries;
    }
}
