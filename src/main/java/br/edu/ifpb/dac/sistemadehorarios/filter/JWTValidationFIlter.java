package br.edu.ifpb.dac.sistemadehorarios.filter;

import br.edu.ifpb.dac.sistemadehorarios.component.TokenComponent;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidationFIlter extends BasicAuthenticationFilter {

    private TokenComponent tokenComponent;

    public JWTValidationFIlter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.tokenComponent =new TokenComponent();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader("Authorization");

        if(attribute == null || !attribute.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String token  = attribute.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }catch (Exception error){
            response.setStatus(401);
            response.getWriter().write("Nao autorizado");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String email = this.tokenComponent.getUserEmail(token);
        if(email==null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
    }
}
