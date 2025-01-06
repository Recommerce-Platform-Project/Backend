package com.recplatform.recplatformproject.auth.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${spring.jwt.secret}")
    private String jwtSecretKey;
    @Value("${spring.jwt.accessTokenExpirationTime}")
    private Long jwtAccessTokenExpirationTime;
    @Value("${spring.jwt.refreshTokenExpirationTime}")
    private Long jwtRefreshTokenExpirationTime;

    /**
     * claim:
     * JWT의 Payload에 커스텀 정보를 추가합니다.
     * 예: 사용자 ID, 이메일, 권한 등.
     *
     * signWith:
     * JWT에 무결성을 보장하기 위해 서명을 생성합니다.
     * 해싱 알고리즘(HMAC-SHA512)과 비밀 키를 사용.
     *
     * compact:
     * JWT의 Header, Payload, Signature를 합쳐 최종 문자열을 반환합니다.
     */
    public String generateAccessToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + jwtAccessTokenExpirationTime);
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .claim("user-id", customUserDetails.getUserID())
                .claim("user-email", customUserDetails.getUserEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + jwtRefreshTokenExpirationTime);
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .claim("user-id", customUserDetails.getUserID())
                .claim("user-email", customUserDetails.getUserEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    // JWT 토큰에 담긴 정보를 jwtSecretKey 를 통해 복호화하고 반환하는 부분
    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("user-id", String.class);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUserEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("user-email", String.class);
    }

    public Date getExpirationFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    //토큰 오류 검출
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("토큰 서명 오류");
        } catch (MalformedJwtException ex) {
            System.out.println("토큰 손상");
        } catch (ExpiredJwtException ex) {
            System.out.println("토큰 만료 오류");
        } catch (UnsupportedJwtException ex) {
            System.out.println("토큰 복호화를 지원하지 않음");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claim 문자열비 비어있음");
        }
        return false;
    }
}
