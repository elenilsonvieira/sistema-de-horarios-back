package br.edu.ifpb.dac.sistemadehorarios.component;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@Component
public class TokenComponent {
    private long expiration = 60;
    private String secret="&0M0e7@9n$#sl%1IG59Zwa7cvO0T541fcR$^";


    public String generate(UserModel userModel){
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(expiration);
        Instant expirationInstant = expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expirationInstant);

        return Jwts
                .builder()
                .setExpiration(expirationDate)
                .setSubject(userModel.getUuid())
                .claim("userUuid",userModel.getUuid())
                .claim("roles", userModel.getRoleEnum())
                .claim("name", userModel.getName())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValid(String token) {
        if(token == null) return false;
        try{
            Claims claims = this.getClaims(token);
            LocalDateTime expirationDate = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(expirationDate);
        }catch (Exception error){
            return false;
        }
    }

    public String getUserUuid(String token){
        Claims claims = this.getClaims(token);
        return claims.getSubject();
    }

    public String getUserEmail(String token){
        Claims claims = this.getClaims(token);
        return (String) claims.get("userEmail");
    }

    public String get(HttpServletRequest request) throws UserInvalidException {
        String token = request.getHeader("Authorization");

        if(token == null || token.startsWith("Bearer")){
            throw new UserInvalidException("Token inválido", 401);
        }
        return token.split(" ")[1];
    }

}
