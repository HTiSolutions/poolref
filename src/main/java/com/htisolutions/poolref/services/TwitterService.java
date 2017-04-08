package com.htisolutions.poolref.services;


import com.htisolutions.poolref.entities.TwitterToken;
import com.htisolutions.poolref.entities.TwitterTokenDao;
import com.htisolutions.poolref.entities.User;
import com.htisolutions.poolref.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TwitterService {
    private TwitterTokenDao twitterTokenDao;
    private UserDao userDao;
    private Twitter twitter;

    @Autowired
    TwitterService(TwitterTokenDao twitterTokenDao, UserDao userDao){
        this.userDao = userDao;
        this.twitterTokenDao = twitterTokenDao;
        twitter = TwitterFactory.getSingleton();
    }

    public void postToTwitter(String message, User user){
        Long tokenId = user.getTwitterTokenId();
        if (tokenId != null){
            TwitterToken twitterToken = twitterTokenDao.findOne(tokenId);
            AccessToken accessToken = new AccessToken(twitterToken.getToken(), twitterToken.getTokenSecret(), twitterToken.getTwitterId());
            twitter.setOAuthAccessToken(accessToken);
            try {
                twitter.updateStatus(message);
            }catch (TwitterException e){

            }
        }
    }

    public void saveTwitterToken(User user, AccessToken accessToken){
        TwitterToken token =  new TwitterToken(accessToken.getUserId(),accessToken.getToken(), accessToken.getTokenSecret());
        TwitterToken savedToken = twitterTokenDao.save(token);
        user.setTwitterTokenId(savedToken.getId());
        userDao.save(user);
    }
}
