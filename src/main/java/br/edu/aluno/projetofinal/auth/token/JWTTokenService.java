package br.edu.aluno.projetofinal.auth.token;

import br.edu.aluno.projetofinal.auth.token.models.Token;
import br.edu.aluno.projetofinal.auth.token.models.TokenPayload;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTokenService implements TokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public Token getToken(TokenPayload tokenPayload, long expirationMs) {
        var expirationDate = new Date(expirationMs + System.currentTimeMillis());
        String jwtSecretMask = "%s:%s";
        return new Token(Jwts.builder()
                .setSubject(tokenPayload.getSubject())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact(), expirationDate);
    }

    @Override
    public TokenPayload validateToken(String token) {
        try {
            var claims = Jwts.parser().
                    setSigningKey(jwtSecret).
                    parseClaimsJws(token).
                    getBody();

            return new TokenPayload(claims.getSubject());
        } catch (Throwable e) {
            System.out.printf("Validate token fail : %s%n", e.getMessage());
        }

        return null;
    }
}
