package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.League;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.LeagueDao;
import com.htisolutions.poolref.repositories.UserLeagueMappingRepository;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubLeaguesService {

    private GameService gameService;
    private UserLeagueMappingRepository userLeagueMappingRepository;
    private UserService userService;

    @Autowired
    SubLeaguesService(GameService gameService, UserLeagueMappingRepository userLeagueMappingRepository, LeagueDao leagueDao) {
        this.gameService = gameService;
        this.userLeagueMappingRepository = userLeagueMappingRepository;
        this.userService = userService;
    }


    public Map<String, List<LeaderBoardEntryViewModel>> calculateSubLeagues(User user) {
        Iterable<League> subLeagues = userLeagueMappingRepository.findAllLeaguesByUserId(user.getId());
        Map<String, List<LeaderBoardEntryViewModel>> subLeagueTables = new HashMap<String, List<LeaderBoardEntryViewModel>>();
        for (League subLeague : subLeagues) {
            subLeagueTables.put(subLeague.getName(), calculateSubLeague(subLeague));
        }
        return subLeagueTables;
    }

    private List<LeaderBoardEntryViewModel> calculateSubLeague(League subLeague) {
        HashMap<Long, LeaderBoardEntryViewModel> subLeagueTable = new HashMap<>();
        Iterable<Game> games = gameService.getGames();
        for (User user : userLeagueMappingRepository.findAllUsersByLeagueId(subLeague.getId())) {
            LeaderBoardEntryViewModel entry = new LeaderBoardEntryViewModel(user);
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

    public long createLeague(String name) {
      return userLeagueMappingRepository.saveLeague(name);
    }

    public Boolean addPlayers(long leagueId, long userId) {
        for(User user : userLeagueMappingRepository.findAllUsersByLeagueId(leagueId)) {
            if(userId == user.getId()) {
                return false;
            }
        }
        return userLeagueMappingRepository.addPlayers(leagueId, userId);
    }

    public Iterable<User> getPlayers(long id) {
        return userLeagueMappingRepository.findAllUsersByLeagueId(id);
    }

    public String getLeagueName(long id) {
      return userLeagueMappingRepository.getLeagueName(id);
    }

    public Iterable<String> getLeagues(User user) {
        List<String> leagues = new ArrayList<String>();
        for (League mapping : userLeagueMappingRepository.findAllLeaguesByUserId(user.getId())) {
            String name = mapping.getName();
            leagues.add(name);
        }
        return leagues;
    }
}
