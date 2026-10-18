package ptzt.f1hub_login.infrastructure.adapter.out.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import ptzt.f1hub_login.application.ports.out.TokenGenerator;
import ptzt.f1hub_login.infrastructure.adapter.dto.out.AuthToken;
import ptzt.f1hub_login.domain.entity.Account;
import ptzt.f1hub_login.infrastructure.adapter.config.JwtProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class JwtTokenGenerator implements TokenGenerator {

    private static final int MIN_SECRET_BYTES = 32; // 256 bits

    private final JwtProperties properties;
    private final SecretKey key;

    public JwtTokenGenerator(JwtProperties properties) {
        this.properties = properties;

        byte[] secret = properties.secret().getBytes(StandardCharsets.UTF_8);
        if (secret.length < MIN_SECRET_BYTES) {
            throw new IllegalStateException(
                    "app.jwt.secret debe tener al menos " + MIN_SECRET_BYTES + " bytes");
        }
        this.key = Keys.hmacShaKeyFor(secret);
    }

    @Override
    public AuthToken generate(Account account) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(properties.expiration());

        String token = Jwts.builder()
                .subject(String.valueOf(account.getId()))
                .issuer(properties.issuer())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiresAt))
                .id(UUID.randomUUID().toString())
                .claim("username", account.getUsername())
                .claim("roles", List.of(account.getAccountRoles().name()))
                .signWith(key, Jwts.SIG.HS256)
                .compact();

        return new AuthToken(
                token,
                LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault())
        );
    }
}