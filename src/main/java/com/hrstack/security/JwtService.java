package com.hrstack.security;

import com.hrstack.exceptions.InvalidRequestException;
import com.hrstack.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JwtProperties jwtProperties;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        try {
            privateKey = loadPrivateKey(jwtProperties.getPrivateKeyPath());
            publicKey = loadPublicKey(jwtProperties.getPublicKeyPath());

            log.info("Private & Public key loaded successfully");
        } catch (final Exception e) {
            log.error("Error loading private key", e);
            throw new RuntimeException("Error loading private key", e);
        }
    }

    public String generateAccessToken(@Nonnull final String workspaceUrl, final String userId, final String role) {
        final Date now = new Date();
        final Date expiration = new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpiration());

        return Jwts.builder()
                .subject(workspaceUrl)
                .claim("user_id", userId)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiration)
                .issuer("hrstack-app")
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();

    }

    public String generateRefreshToken(@Nonnull final String workspaceUrl, @Nonnull final String userId, final String role) {
        final Date now = new Date();
        final Date expiration = new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExpiration());

        return Jwts.builder()
                .subject(userId)
                .claim("workspace_url", workspaceUrl)
                .claim("role", role)
                .claim("tokenType", "refresh")
                .issuedAt(now)
                .expiration(expiration)
                .issuer("hrstack-app")
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();

    }
    public String getUserIdFromRefreshToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getUserIdFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.get("user_id", String.class);
    }

    public String getWorkspaceUrlFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getRoleFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.get("role", String.class);
    }

    public boolean validateToken(final String token) {
        Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token);
        return true;
    }
    private Claims getClaimsFromToken(final String token) {
        Claims claims = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        System.out.println("JWT CLAIMS = " + claims);
        return claims;
    }

    public boolean isRefreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null) return false;

        return "refresh".equals(String.valueOf(claims.get("tokenType")));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }

    public String generatePasswordResetToken(String email) {
        return Jwts.builder()
                .subject(email)
                .claim("purpose", "PASSWORD_RESET")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public boolean validatePasswordResetToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String type = claims.get("purpose", String.class);
        if (!"PASSWORD_RESET".equals(type)) {
            throw new InvalidRequestException("Invalid reset token");
        }
        if (claims.getExpiration().before(new Date())) {
            throw new InvalidRequestException("Reset token expired");
        }
        return true;
    }

    public String getEmailFromResetToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String purpose = claims.get("purpose", String.class);
        if (!"PASSWORD_RESET".equals(purpose)) {
            throw new InvalidRequestException("Invalid reset token");
        }
        return claims.getSubject();
    }

    private PrivateKey loadPrivateKey(final String privateKeyPath) throws Exception {
        try (final InputStream is = JwtService.class.getClassLoader()
                .getResourceAsStream(privateKeyPath)) {

            if (is == null) {
                throw new RuntimeException("Private key not found");
            }

            final String key = new String(is.readAllBytes());
            final String privateKeyPEM = key
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            final byte[] encoded = Base64.getDecoder()
                    .decode(privateKeyPEM);
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return KeyFactory.getInstance("RSA")
                    .generatePrivate(keySpec);
        }
    }

    private PublicKey loadPublicKey(final String publicKeyPath) throws Exception {
        try (final InputStream is = JwtService.class.getClassLoader()
                .getResourceAsStream(publicKeyPath)) {
            if (is == null) {
                throw new RuntimeException("Public key not found");
            }
            final String key = new String(is.readAllBytes());
            final String publicKeyPEM = key
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            final byte[] encoded = Base64.getDecoder()
                    .decode(publicKeyPEM);
            final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return KeyFactory.getInstance("RSA")
                    .generatePublic(keySpec);
        }
    }
}
