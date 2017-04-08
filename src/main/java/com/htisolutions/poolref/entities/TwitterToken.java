package com.htisolutions.poolref.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TwitterToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="twitter_id")
    private Long twitterId;

    @NotNull
    @Column(name="token")
    private String token;

    @NotNull
    @Column(name="token_secret")
    private String tokenSecret;

    public TwitterToken(Long twitterId, String token, String tokenSecret){
        this.twitterId = twitterId;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public Long getId(){ return id;}

    public Long getTwitterId(){ return twitterId;}

    public String getToken(){ return token;}

    public String getTokenSecret(){ return tokenSecret;}
}
