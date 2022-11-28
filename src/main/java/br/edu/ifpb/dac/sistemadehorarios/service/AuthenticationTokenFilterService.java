package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationTokenFilterService extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        boolean isValid = this.tokenService.isValidToken(token);

        if (isValid) {
            authenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        String uuid = tokenService.getUuid(token);
        UserModel user = userService.findByUuid(uuid);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        int length = token.length();
        return token.substring(7, length);
    }
}
