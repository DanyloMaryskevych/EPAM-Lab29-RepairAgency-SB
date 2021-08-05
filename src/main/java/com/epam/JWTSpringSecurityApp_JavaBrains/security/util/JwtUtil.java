package com.epam.JWTSpringSecurityApp_JavaBrains.security.util;

import com.epam.JWTSpringSecurityApp_JavaBrains.security.model.User;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.repository.UserRepository;
import com.epam.JWTSpringSecurityApp_JavaBrains.security.util.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private final String SECRET_KEY = "secret";
    private final String JWT_BEGINNING = "Bearer ";
    private final UserRepository userRepository;


    @Autowired
    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Token generation and creation
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findUserByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        claims.put("id", user.getId());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    // Extracting information from token
    public String getToken(HttpServletRequest request) throws InvalidTokenException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            return authorization.substring(JWT_BEGINNING.length());
        }
        else throw new InvalidTokenException("Token does not exists!");
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private <T> T getSomeClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUsername(String token) {
        return getSomeClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return getSomeClaim(token, Claims::getExpiration);
    }

    public Object getId(String token) {
        return getSomeClaim(token, claims -> claims.get("id"));
    }

    // Token Validation
    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
