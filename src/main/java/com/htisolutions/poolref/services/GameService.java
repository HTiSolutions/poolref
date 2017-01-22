package com.htisolutions.poolref.services;

import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.Date;

@Service
public class GameService {

    private GameDao gameDao;

    @Autowired
    GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void gameSave(Date date, Long winnerId, Long loserId) {
        try {
            Game game = new Game(date, winnerId, loserId);
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
