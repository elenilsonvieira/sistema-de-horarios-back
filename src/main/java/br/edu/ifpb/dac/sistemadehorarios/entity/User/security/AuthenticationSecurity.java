package br.edu.ifpb.dac.sistemadehorarios.entity.User.security;

import br.edu.ifpb.dac.sistemadehorarios.component.TokenComponent;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSecurity {

    private final AuthenticationManager authenticationManager;
    private final TokenComponent tokenComponent;
    private final UserService userService;

    public AuthenticationSecurity(AuthenticationManager authenticationManager, TokenComponent tokenComponent, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenComponent = tokenComponent;
        this.userService = userService;
    }

    public String login(String username, String password){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        UserModel user = this.userService.findByEmail(username);
        return this.tokenComponent.generate(user);
    }

    public UserModel getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserModel) authentication.getPrincipal();
    }
}
