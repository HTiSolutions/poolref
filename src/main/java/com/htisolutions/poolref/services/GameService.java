package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.Game;
import com.htisolutions.poolref.models.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class GameService {

    private GameDao gameDao;

    @Autowired
    GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void gameSave(String winner, String loser) {
        try {
            Game game = new Game(winner, loser);
            gameDao.save(game);
        } catch (Exception ex) {
            //"Error saving the game: " + ex.toString();
        }
    }
}
