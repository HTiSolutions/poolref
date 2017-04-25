package com.htisolutions.poolref.services;


import com.htisolutions.poolref.entities.TwitterToken;
import com.htisolutions.poolref.entities.TwitterTokenDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TwitterService {
    private TwitterTokenDao twitterTokenDao;
    private UserDao userDao;

    @Autowired
    TwitterService(TwitterTokenDao twitterTokenDao, UserDao userDao){
        this.userDao = userDao;
        this.twitterTokenDao = twitterTokenDao;
    }

    public String addOpponentTag(String message, User player, User user){
        if (user.getId() != player.getId()){
            Long tokenId = player.getTwitterTokenId();
            if (tokenId != null){
                String name = twitterTokenDao.findOne(tokenId).getTwitterName();
                message += " @"+name;
            }
        }
        return message;
    }

    public boolean postToTwitter(String message, boolean tagOpponent,  User winner, User loser){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long tokenId = user.getTwitterTokenId();
        boolean messagePresent = message.length() > 0;
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        if (tokenId != null){
            TwitterToken twitterToken = twitterTokenDao.findOne(tokenId);
            AccessToken accessToken = new AccessToken(twitterToken.getToken(), twitterToken.getTokenSecret());
            twitter.setOAuthConsumer("aqFIw1MyHnpaYO717JWLv36KZ", "1ykTKVJ20k03Grrkn6rYVLS314QbcC3SiDto7TfvpVIiPOmX3Q");
            twitter.setOAuthAccessToken(accessToken);
            try {
                if (messagePresent) {
                    if (tagOpponent){
                        message = addOpponentTag(message, winner, user);
                        message = addOpponentTag(message, loser, user);
                    }
                    message += " #poolref";
                    twitter.updateStatus(message);
                }
                return true;
            }catch (TwitterException e){
                return false;
            }
        }
        if(messagePresent) {
            return false;
        }else{
            return true;
        }
    }

    public void saveTwitterToken(User user, AccessToken accessToken){
        TwitterToken token =  new TwitterToken(accessToken.getScreenName(),accessToken.getToken(), accessToken.getTokenSecret());
        TwitterToken savedToken = twitterTokenDao.save(token);
        user.setTwitterTokenId(savedToken.getId());
        userDao.save(user);
    }
}
