package com.creamcheese.crackers.jwt;

import com.creamcheese.crackers.dto.token.TokenDTO;
import com.creamcheese.crackers.exception.CustomExcetpion;
import com.creamcheese.crackers.exception.ErrorCode;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final RedisUtil redisUtil;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    private long accessTokenValidTime = 60 * 60 * 24 * 1000L;               // 24 hour
    private long refreshTokenValidTime = 60 * 60 * 24 * 7 * 1000L;          // 7 days

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String loginId){

        return createToken(loginId, accessTokenValidTime);
    }

    public String createRefreshToken(String loginId){
        String refreshToken = createToken(loginId, refreshTokenValidTime);
        redisUtil.setRedisRefreshToken(loginId, refreshToken);
        return refreshToken;
    }

    public String createToken(String loginId, long validTime){
        Date now = new Date();
        Date validity = new Date(now.getTime()+validTime);

        Claims claims = Jwts.claims()
                .setSubject(loginId);

        claims.put("loginId", loginId);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims != null;
        } catch (SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다." + e);
            throw new CustomExcetpion(ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
            throw new CustomExcetpion(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Authentication getAuthentication(String token){
        try{
            PrincipalDetails principalDetails = userDetailsService.loadUserByUsername(this.getTokenLoginId(token));
            return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


    // Token -> login Id 꺼내기
    public String getTokenLoginId(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("loginId");
    }


    // Redis 에 해당 유저의 RefreshToken 존재하는지 여부
    public boolean existRefreshToken(String refreshToken) {
        String key = getTokenLoginId(refreshToken);  // key = email
        String redisRefreshToken = redisUtil.getRedisRefreshToken(key);

        if(refreshToken.equals(redisRefreshToken)) return true;
        return false;
    }


    // Access & Refresh Token 재발급
    public TokenDTO reissueToken(String refreshToken) {
        boolean validRefreshToken = this.validateToken(refreshToken);
        boolean existRefreshToken = this.existRefreshToken(refreshToken);

        if(validRefreshToken && existRefreshToken) {
            String loginId = this.getTokenLoginId(refreshToken);

            String newAccessToken = this.createAccessToken(loginId);
            String newRefreshToken = this.createRefreshToken(loginId);

            // Redis 업데이트
            redisUtil.setRedisRefreshToken(loginId, newRefreshToken);

            return new TokenDTO(newAccessToken, newRefreshToken);

        }

        // refresh token도 만료 ->  로그인 필요
        throw new RuntimeException("리프레시 토큰 만료, 재로그인 해주세요");
    }


    // token 의 남은 유효 시간 구하기
    public long getExpiration(String token) {
        Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
        Long now = new Date().getTime();

        return (expiration.getTime() - now);
    }

}
