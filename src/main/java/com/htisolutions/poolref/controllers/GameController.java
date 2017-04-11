package com.htisolutions.poolref.controllers;
import com.htisolutions.poolref.models.GameEntry;
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
    public HttpEntity<byte[]> photo() {

        try {
            URL url = GameController.class.getResource("/images/rectangle.png");
            BufferedImage img = ImageIO.read(url);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( img, "png", baos );
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
