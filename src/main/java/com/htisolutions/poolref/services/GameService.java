package com.htisolutions.poolref.services;

import com.htisolutions.poolref.models.Game;
import com.htisolutions.poolref.models.User;
import com.htisolutions.poolref.models.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GameService {

    private GameDao gameDao;

    @Autowired
    GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void gameSave(Long winnerId, Long loserId) {
        try {
            Game game = new Game(winnerId, loserId);
            gameDao.save(game);
        } catch (Exception ex) {
            //"Error saving the game: " + ex.toString();
        }
    }

    public Iterable<Game> getGames() {
        Iterable<Game> games = gameDao.findAll();
        return games;
    }
}
