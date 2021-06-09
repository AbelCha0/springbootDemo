package com.yisquare.springboot.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
public class JwtUtil {

    public static final long EXPIRE_TIME = 10 * 60 * 1000;

    public static final String SECRET = "AUYB(&%T%43SKQLJH*7P0#!123";

    public static boolean verify(String token) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userCode", getUserCode(token))
                    .build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserCode(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userCode").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @param userCode 用户Code
     * @return 加密的token
     */
    public static String sign(String userCode) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("userCode", userCode)
                .withExpiresAt(date)
                .sign(algorithm);

    }

//    public static void main(String[] args) throws Exception {
//		 String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjMwNTk1MzAsInVzZXJDb2RlIjoiYWRtaW4ifQ.a4mNYdxGjADH2ND5mlWPN-mSCHmN4jGnZKzkuAQmzQo";
//		 System.out.println(JwtUtil.getUserCode(token));
//
//        String token2 = JwtUtil.sign("admin", "e10Cadc3949ba59abbe56e057f20Cf883e");
//        System.out.println(token);
//        System.out.println( JwtUtil.verify(token2,"e10Cadc3949ba59abbe56e057f20Cf883e"));
//    }
}


