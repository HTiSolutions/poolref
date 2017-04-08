package com.htisolutions.poolref.controllers;

import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.*;


@Controller
@RequestMapping("/twitter")
public class TwitterController {

    private RequestToken requestToken;
    private Twitter twitter;
    private TwitterService twitterService;

    @Autowired
    TwitterController(TwitterService twitterService){
        this.twitterService = twitterService;
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("aqFIw1MyHnpaYO717JWLv36KZ", "1ykTKVJ20k03Grrkn6rYVLS314QbcC3SiDto7TfvpVIiPOmX3Q");
    }

    @RequestMapping()
    public String index(){
        try {
            requestToken = twitter.getOAuthRequestToken();
            return ("redirect:/twitter/signIn");
        }catch (TwitterException e){

        }
        return "register";
    }

    @RequestMapping("/signIn")
    public ModelAndView signIn(){
        String url = "redirect:" + requestToken.getAuthenticationURL();
        return new ModelAndView(url);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/callback")
    public String callBack(
            @RequestParam(name = "oauth_verifier") String verifier
    ){
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(verifier);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            twitterService.saveTwitterToken(user, accessToken);
        }
        catch (TwitterException e){

        }
        return ("redirect:/leaderboard");
    }

}
