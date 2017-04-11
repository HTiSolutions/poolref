package com.htisolutions.poolref.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.GameDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.JSON.GameData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.util.Date;

@Service
public class GameService {

    private GameDao gameDao;

    private UserDao userDao;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GameService(GameDao gameDao, UserDao userDao) {
        this.gameDao = gameDao;
        this.userDao = userDao;
    }

    public GameEntry getGameEntryById(Long id) {
        Game game = gameDao.findOne(id);
        User winner = userDao.findOne(game.getWinnerId());
        User loser = userDao.findOne(game.getLoserId());
        GameEntry gameEntry = new GameEntry(winner, loser, game);

        String gameDataJson = game.getGameData();
        if (gameDataJson != null) {
            GameData gameData = parseGameData(gameDataJson);
            gameEntry.setGameData(gameData);
        }

        return gameEntry;
    }

    public Boolean gameSave(Date date, Long winnerId, Long loserId) {
        if (winnerId != loserId) {
            try {
                Game game = new Game(date, winnerId, loserId);
                gameDao.save(game);
            } catch (Exception ex) {
                log.error("Error saving the game: {}", ex.toString());
            }
            return true;
        }
        return false;
    }

    public void gameDelete(Long gameId) {
        try {
            gameDao.delete(gameId);
        } catch (Exception ex) {
            log.error("Error deleting the game: {}", ex.toString());
        }
    }

    public Iterable<Game> getGames() {
        return gameDao.findAll();
    }

    private GameData parseGameData(String jsonData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, GameData.class);
        } catch (Exception ex) {
            log.error("Error parsing json: {}", ex.toString());
        }

        return null;
    }
}
