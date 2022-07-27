package br.edu.ifpb.dac.sistemadehorarios.component;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
/*Anotação genérica para qualquer componente gerenciado pelo Spring.
Esta anotação faz com que o bean registrado no Spring possa ser utilizado em qualquer bean,
seja ele um serviço, um DAO, um controller, etc.
No nosso exemplo, ele será responsável por um Bean que representa uma entidade.*/
@Component
public class TokenComponent {
    private final String USER_UUID = "userUuid";
    private final String USER_EMAIL = "userEmail";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generate(UserModel userModel){
        long expiration = Long.valueOf(this.expiration);
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(expiration);
        Instant expirationInstant = expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expirationInstant);

        return Jwts
                .builder()
                .setExpiration(expirationDate)
                .setSubject(userModel.getUuid())
                .claim(USER_UUID,userModel.getUuid())
                .claim(USER_EMAIL,userModel.getEmail())
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
        return (String) claims.get(this.USER_EMAIL);
    }

    public String get(HttpServletRequest request) throws UserInvalidException {
        String token = request.getHeader("Authorization");

        if(token == null || token.startsWith("Bearer")){
            throw new UserInvalidException("Token inválido", 401);
        }
        return token.split(" ")[1];
    }

}
