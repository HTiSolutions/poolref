package com.htisolutions.poolref.controllers;

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

    RequestToken requestToken;
    Twitter twitter;

    @RequestMapping()
    public String index(){
        System.out.printf("Twitter");
        // The factory instance is re-useable and thread safe.
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("aqFIw1MyHnpaYO717JWLv36KZ", "1ykTKVJ20k03Grrkn6rYVLS314QbcC3SiDto7TfvpVIiPOmX3Q");
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
            //save token, token secret and user Id to db
            //twitter.setOAuthAccessToken(accessToken);
            //Twitter x = TwitterFactory.getSingleton();
            //x.setOAuthConsumer("aqFIw1MyHnpaYO717JWLv36KZ", "1ykTKVJ20k03Grrkn6rYVLS314QbcC3SiDto7TfvpVIiPOmX3Q");
            //x.setOAuthAccessToken(accessToken);
        }
        catch (TwitterException e){

        }

        return ("redirect:/login");
    }

}
