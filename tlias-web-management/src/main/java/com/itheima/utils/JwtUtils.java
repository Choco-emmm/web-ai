package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

/**
 * JWT令牌操作工具类
 */
public class JwtUtils {
    
    // 签名密钥（与测试类中一致）
    private static final String SECRET_KEY = "aXRoZWltYQ==";
    
    // 令牌过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;
    
    /**
     * 生成JWT令牌
     * @param claims 要存储的数据（键值对）
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }
    
    /**
     * 解析JWT令牌
     * @param token JWT令牌字符串
     * @return 解析后的Claims对象（包含所有存储的数据）
     * @throws io.jsonwebtoken.JwtException 如果令牌无效或已过期
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * 验证JWT令牌是否有效
     * @param token JWT令牌字符串
     * @return true: 有效, false: 无效或已过期
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 从令牌中获取指定字段的值
     * @param token JWT令牌字符串
     * @param key 字段名
     * @return 字段值（可能为null）
     */
    public static Object getClaimFromToken(String token, String key) {
        Claims claims = parseToken(token);
        return claims.get(key);
    }
    
    /**
     * 获取令牌的过期时间
     * @param token JWT令牌字符串
     * @return 过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }
    
    /**
     * 判断令牌是否已过期
     * @param token JWT令牌字符串
     * @return true: 已过期, false: 未过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}