package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.UserStat;
import com.htisolutions.poolref.viewModels.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private GameService gameService;
    private UserService userService;

    @Autowired
    ProfileService(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }
    public ProfileViewModel generateProfile (User user){
        UserStat stat = new UserStat(user, gameService.getGames());
        List<GameEntry> gamesInvolved = calculateGamesInvolved(user);
        Boolean enableButton = checkProfileID(user);
        return new ProfileViewModel(user, stat, gamesInvolved, enableButton);
    }

    private List <GameEntry> calculateGamesInvolved(User user){
        Iterable <Game> games = gameService.getGames();
        List <GameEntry> gamesInvolved = new ArrayList();
        for (Game game : games){
            if (game.getWinnerId() == user.getId() || game.getLoserId() == user.getId()){
                gamesInvolved.add(new GameEntry(userService.getUserById(game.getWinnerId()),userService.getUserById(game.getLoserId()), game));
            }
        }
        return gamesInvolved;
    }

    private Boolean checkProfileID(User user){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentId = currentUser.getId();

        if(user.getId() == currentId){
            return true;
        }
        return false;
    }

}
