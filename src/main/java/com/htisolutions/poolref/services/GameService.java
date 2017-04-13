package com.htisolutions.poolref.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htisolutions.poolref.controllers.GameController;
import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.entities.GameDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.JSON.Ball;
import com.htisolutions.poolref.models.JSON.GameData;
import com.htisolutions.poolref.models.JSON.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

    public byte[] createPoolTableImage(Long gameId, Integer stateId) {
        try {
            String resourcePath = GameController.class.getResource("/images/").getPath();

            BufferedImage table = ImageIO.read(new File(resourcePath, "pool_table.png"));
            BufferedImage output = new BufferedImage(table.getWidth(), table.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics g = output.getGraphics();
            g.drawImage(table, 0, 0, null);

            GameEntry gameEntry = this.getGameEntryById(gameId);
            GameState gameState = gameEntry.getGameData().getGameStates().get(stateId);

            Double cushionSize = 60.0;
            Double ballSize = 40.0;

            for (Ball ball : gameState.getBallLocations()) {
                String fileName = String.format("b%d.png", ball.getBallNum());
                BufferedImage ballImage =  ImageIO.read(new File(resourcePath + "balls/", fileName));
                Image image = ballImage.getScaledInstance(ballSize.intValue(), ballSize.intValue(), Image.SCALE_DEFAULT);

                Double playingX = (table.getWidth() - (2*cushionSize));
                Double playingY = (table.getHeight() - (2*cushionSize));

                Double centreBallX = ((playingX/100) * ball.getX()) + (cushionSize);
                Double centreBallY = ((playingY/100) * ball.getY()) + (cushionSize);

                Long adjustment = Math.round(ballSize/2 * Math.cos(Math.PI/4));
                Integer ballX = centreBallX.intValue() - adjustment.intValue();
                Integer ballY = centreBallY.intValue() - adjustment.intValue();

                g.drawImage(image, ballX, ballY, null);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(output, "png", baos );
            baos.flush();
            byte[] image = baos.toByteArray();
            baos.close();

            return image;
        } catch (IOException ex) {
            log.error("Error creating pool table image: {}", ex.toString());
        }

        return null;
    }
}
