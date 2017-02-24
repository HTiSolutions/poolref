package com.htisolutions.poolref.services;
import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.SubLeague;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.viewModels.LeaderBoardEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SubLeaguesService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    SubLeaguesService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    public Iterable <SubLeague> getSubLeaguesByUser(User user){
        Iterable<SubLeague> subLeagues = null;
        return subLeagues;
    }

    public List<List<LeaderBoardEntryViewModel>> calculateSubLeageues(User user){
        Iterable<SubLeague>subLeagues = getSubLeaguesByUser(user);
        List<List<LeaderBoardEntryViewModel>> subleagueTables = new ArrayList<>();
        for (SubLeague subLeague : subLeagues){
            subleagueTables.add(calculateSubLeageue(subLeague));
        }
        return subleagueTables;
    }

    private List<LeaderBoardEntryViewModel> calculateSubLeageue(SubLeague subLeague){
        Iterable<User>usersInLeague = subLeague.getUsersInLeague();
        HashMap<Long, LeaderBoardEntryViewModel> subLeagueTable = new HashMap<>();
        Iterable<Game> games = gameService.getGames();
        for (User user: usersInLeague){
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

    private List<LeaderBoardEntryViewModel> generateSortedTable(HashMap<Long, LeaderBoardEntryViewModel> subLeagueTable){
        List<LeaderBoardEntryViewModel> subLeagueEntries = new ArrayList<LeaderBoardEntryViewModel>(subLeagueTable.values());

        Comparator<LeaderBoardEntryViewModel> leaderBoardComparator = Comparator
                .comparing((LeaderBoardEntryViewModel e)-> e.getPercentage())
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
