package com.learnings.markup.markuplive.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.logging.Logger;

@Slf4j
public class JwtUtil {

    private static final String SECRET_KEY = "lxClientAWC";// 密钥
    private static final Logger log = Logger.getLogger(JwtUtil.class.getName());
    private static final Long EXPIRATION_TIME = 604800000L; // 7 days in milliseconds

    /**
     * 生成JWT
     * @param userID 用户的ID
     * @param userName 用户名
     * @return
     */
    public static String generateToken(Long userID ,String userName){
        return Jwts.builder()
                .setSubject(userName)
                .claim("userID", userID)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();


    }

    /**
     * 验证JWT并解析
     */

    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warning("Invalid token: " + e.getMessage());
            throw new RuntimeException("Token is invalid or expired");
        }
    }

    /**
     * 验证JWT是否过期
     */
    public static boolean isTokenExpired(String token) {
        try{
            Date expiration = parseToken(token).getExpiration();
            return expiration.before(new Date());
        }catch (ExpiredJwtException e){
            log.info("Token expired:"+e.getMessage());
            return true;
        }
    }
}
