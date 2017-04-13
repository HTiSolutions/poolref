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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @RequestMapping(value = "/view")
    public ModelAndView view(
            Long id,
            @RequestParam(name = "index", defaultValue = "0", required = false) Integer index) {

        GameEntry gameEntry = gameService.getGameEntryById(id);

        ModelAndView model = new ModelAndView("views/game/view");
        model.addObject("game", gameEntry);
        model.addObject("index", index);
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

        byte[] image = gameService.createPoolTableImage(gameId, stateId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);
        return new HttpEntity<>(image, headers);
    }
}
