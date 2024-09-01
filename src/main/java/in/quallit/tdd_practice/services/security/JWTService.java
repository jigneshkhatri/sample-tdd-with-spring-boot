package in.quallit.tdd_practice.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

/**
 * @author Jigs
 */
@Service
public class JWTService {

    public static final String QUALLIT = "quallit";
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiry-seconds}")
    private Long expirySeconds;

    public String generateToken(String username, Map<String, String> extraClaims) {
        Algorithm algorithm = getAlgorithm();
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(QUALLIT)
                .withSubject(username)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(expirySeconds));
        if (extraClaims != null) {
            for (Map.Entry<String, String> entry: extraClaims.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue());
            }
        }
        return builder.sign(algorithm);
    }

    public String verifyToken(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(QUALLIT)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
