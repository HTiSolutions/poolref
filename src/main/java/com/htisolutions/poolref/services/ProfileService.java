package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.iDontKnowWhereToPutThese.UserStat;
import com.htisolutions.poolref.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private GameService gameService;

    @Autowired
    ProfileService(GameService gameService) {
        this.gameService = gameService;
    }
    public ProfileViewModel generateProfile (User user){
        String firstName = user.getForename();
        String lastName = user.getSurname();
        String nickname = user.getNickname();
        UserStat stat = new UserStat(user.getId(), gameService.getGames());
        Integer gamesPlayed = stat.getGamesPlayed();
        Integer wins = stat.getWins();
        Integer losses = stat.getLosses();
        Float percentage = stat.getPercentage();
        List<Game> gamesInvolved = calculateGamesInvolved(user.getId());
        return new ProfileViewModel(firstName, lastName, nickname, gamesPlayed, wins, losses, percentage, gamesInvolved);
    }

    private List <Game> calculateGamesInvolved(Long id){
        Iterable <Game> games = gameService.getGames();
        List <Game> gamesInvolved = new ArrayList();
        for (Game game : games){
            if (game.getWinnerId() == id || game.getLoserId() == id){
                gamesInvolved.add(game);
            }
        }
        return gamesInvolved;
    }

}
