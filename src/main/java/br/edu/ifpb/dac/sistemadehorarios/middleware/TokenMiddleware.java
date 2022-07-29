package br.edu.ifpb.dac.sistemadehorarios.middleware;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import br.edu.ifpb.dac.sistemadehorarios.component.TokenComponent;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenMiddleware extends OncePerRequestFilter {

    private TokenComponent tokenComponent;
    private UserService userService;

    public TokenMiddleware(TokenComponent tokenComponent, UserService userService) {
        this.tokenComponent = tokenComponent;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = this.tokenComponent.get(request);
            boolean isValid = tokenComponent.isValid(token);

            if(isValid){
                authenticate(token);
            }
        } catch (UserInvalidException e) {
            e.printStackTrace();
        }finally {
            filterChain.doFilter(request, response);
        }
    }

    private void authenticate(String token){
        String userUuid= this.tokenComponent.getUserUuid(token);
        UserModel user = this.userService.findByUuid(userUuid);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
