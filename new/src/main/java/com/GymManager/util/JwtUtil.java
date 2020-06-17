package com.GymManager.util;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;



import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import java.util.Map;

import java.util.function.Function;



@Component
public class JwtUtil {
	private String SECRET_KEY = "secret";



    /**

     * 生成token

     * @param subject jwt的payload部分

     * @return token字符串

     */

    public String generateToken(String subject, Map<String, Object> claims) {

        return createToken(claims, subject);

    }



    /**

     * 验证token合法性

     * @param token 浏览器传过来的token

     * @param subject 检查是否和token中一致

     * @return 是否有效

     */

    public Boolean validateToken(String token, String subject) {

        final String username = extractSubject(token);

        return (username.equals(subject) && !isTokenExpired(token));

    }





    public String extractUidSubject(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        String token = null;



        if(authHeader !=null && authHeader.startsWith("Bearer ")){

            token = authHeader.substring(7);

        }



        Claims claims = extractAllClaims(token);

        return claims.get("uid").toString();

    }





    /**

     * 拿出subject（存的是username）

     * @param token token

     * @return subject

     */

    public String extractSubject(String token) {

        return extractClaim(token, Claims::getSubject);

    }



    /**

     * 拿出过期时间

     * @param token token

     * @return 过期时间

     */

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }





    /**

     * 拿出claims

     * @param token token

     * @param claimsResolver claim解析函数

     * @return claims

     */

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);

    }



    /**

     * 拿出所有claims

     * @param token token

     * @return Claims

     */

    private Claims extractAllClaims(String token) {

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

    }



    /**

     * 检查Token是否过期

     * @param token token

     * @return 是否过期

     */

    private Boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }



    /**

     * 创建 Token

     * @param claims  claim

     * @param subject subject

     * @return Token 字符串

     */

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()

                .setClaims(claims)

                .setSubject(subject)

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))

//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 20))

                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)

                .compact();

    }
}
