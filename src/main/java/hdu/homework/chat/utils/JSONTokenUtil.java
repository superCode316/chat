package hdu.homework.chat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JSONTokenUtil implements Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String secret;
    private String header;
    private Long expire;

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), new Date());
    }

    private String generateToken(String username, Date date) {
        Date expireDate = new Date(System.currentTimeMillis() + expire);
        Map<String, Object> map = Map.of("sub", username, "created", date);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null)
            return claims.getSubject();
        else
            return null;
    }

    private boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        if (claims != null){
            Date expire = claims.getExpiration();
            return expire.before(new Date());
        }
        return true;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.info("error get claim");
            return null;
        }
    }
}
