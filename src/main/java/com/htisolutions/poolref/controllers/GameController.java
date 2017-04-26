package com.htisolutions.poolref.controllers;
import com.htisolutions.poolref.entities.Game;
import com.htisolutions.poolref.models.GameEntry;
import com.htisolutions.poolref.models.JSON.Ball;
import com.htisolutions.poolref.models.JSON.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.htisolutions.poolref.services.GameService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    @PostMapping("/upload")
    public String handleFileUpload(Long gameId, MultipartFile file, RedirectAttributes redirectAttributes) {
        String response = "Whoops: Something went wrong..";

        if (validateJSON(file)) {
            try {
                String jsonString = new String(file.getBytes(), "UTF-8");
                Game game = gameService.findOne(gameId);
                game.setGameData(jsonString);
                gameService.gameSave(game);
                return "redirect:/game/view?id=" + gameId;
            } catch (Exception ex) {
                log.error("Error uploading json file: {}", ex.toString());
            }
        } else {
            response = "Invalid file type.";
        }

        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/game/view?id=" + gameId;
    }

    private boolean validateJSON(MultipartFile file) {
        String fileName = file.getOriginalFilename().toUpperCase();
        return fileName.endsWith(".JSON");
    }
}
