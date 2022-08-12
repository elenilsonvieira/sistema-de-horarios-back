package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserService service;

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public String generateTokenJwt(Authentication authentication) throws JsonProcessingException {
        UserModel user = (UserModel) authentication.getPrincipal();
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(Long.parseLong(expiration));
        Instant expirationInstant = expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expirationInstant);
        return Jwts.builder()
                .setIssuer("sistemaDeHorarios")
                .setSubject(user.getUuid())
                .claim("roles", user.getAuthorities())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .setIssuedAt(expirationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String generateTokenJwt(UserModel user) {
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(Long.parseLong(expiration));
        Instant expirationInstant = expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expirationInstant);
        return Jwts.builder()
                .setIssuer("sistemaDeHorarios")
                .setSubject(user.getUuid())
                .claim("roles", user.getAuthorities())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .setIssuedAt(expirationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Collection<? extends GrantedAuthority> getRoles(String token) {
        Claims claims = this.getClaims(token);
        return (Collection<? extends GrantedAuthority>) claims.get("roles");
    }
    public String getEmail(String token) {
        Claims claims = this.getClaims(token);
        return (String) claims.get("email");
    }
    public String getUuid(String token) {
        Claims claims = this.getClaims(token);
        return claims.getSubject();
    }
    public String getName(String token) {
        Claims claims = this.getClaims(token);
        return (String) claims.get("name");
    }

    public void isExpiration(String token) {
        this.getClaims(token);
    }

    private UserModel getUserByUserUuid(String uuid){
        return service.findByUuid(uuid);
    }
    public String refresh(String token, String uuid) throws UserInvalidException {
        try{
            this.isExpiration(token);
            return token;
        }catch (ExpiredJwtException error){
            UserModel user = this.getUserByUserUuid(uuid);
            return this.generateTokenJwt(user);
        }catch (Exception error){
            throw new UserInvalidException("Esse token é inválido", 400);
        }
    }
}
