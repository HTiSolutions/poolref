package com.htisolutions.poolref.controllers;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.JSON.Ball;
import com.htisolutions.poolref.models.JSON.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.htisolutions.poolref.services.GameService;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Controller
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @RequestMapping(value = "/view")
    public ModelAndView view(Long id) {

        GameEntry gameEntry = gameService.getGameEntryById(id);

        ModelAndView model = new ModelAndView("views/game/view");
        model.addObject("game", gameEntry);
        return model;
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {

        gameService.gameDelete(id);

        return ("redirect:/profile");
    }

    @RequestMapping("/photo")
    @ResponseBody
    public HttpEntity<byte[]> photo(Long gameId, Integer stateId) {

        try {
            String resourcePath = GameController.class.getResource("/images/").getPath();

            BufferedImage table = ImageIO.read(new File(resourcePath, "pool_table.png"));
            BufferedImage output = new BufferedImage(table.getWidth(), table.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics g = output.getGraphics();
            g.drawImage(table, 0, 0, null);

            GameEntry gameEntry = gameService.getGameEntryById(gameId);
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


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(image.length);
            return new HttpEntity<>(image, headers);


        } catch (IOException e) {
        }
        return null;

    }
}
