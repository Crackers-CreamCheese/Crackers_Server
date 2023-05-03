package com.creamcheese.crackers.domain.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken", timeToLive = 60)
public class RefreshToken {

    @Id
    private String refreshToken;
    private Integer loginId;

    public RefreshToken(String refreshToken, Integer loginId){
        this.refreshToken = refreshToken;
        this.loginId = loginId;
    }

    public String getRefreshToken(){
        return refreshToken;
    }

    public Integer getLoginId(){
        return loginId;
    }
}
