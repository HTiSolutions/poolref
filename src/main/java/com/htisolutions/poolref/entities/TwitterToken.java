package com.htisolutions.poolref.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TwitterToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="twitter_name")
    private String twitterName;

    @NotNull
    @Column(name="token")
    private String token;

    @NotNull
    @Column(name="token_secret")
    private String tokenSecret;

    public TwitterToken() { }

    public TwitterToken(Long id) {
        this.id = id;
    }

    public TwitterToken(String twitterName, String token, String tokenSecret){
        this.twitterName = twitterName;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public Long getId(){ return id;}

    public String getTwitterName(){ return twitterName;}

    public String getToken(){ return token;}

    public String getTokenSecret(){ return tokenSecret;}
}
