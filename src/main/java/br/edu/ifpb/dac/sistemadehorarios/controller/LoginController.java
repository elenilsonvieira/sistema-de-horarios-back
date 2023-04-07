package br.edu.ifpb.dac.sistemadehorarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.sistemadehorarios.DTO.LoginDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.TokenDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.service.LoginService;
import br.edu.ifpb.dac.sistemadehorarios.service.TokenService;

@RestController
@RequestMapping("/auth")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            UserModel user = loginService.login(loginDTO.getEnrollment(), loginDTO.getPass());
            String token = tokenService.generateTokenJwt(user);

            UserDTO userDTO = UserDTO.builder()
                    .name(user.getName())
                    .enrollment(user.getEnrollment())
                    .token(token)
                    .build();

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getStackTrace());
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity isTokenValid(@RequestBody TokenDTO tokenDTO) throws UserInvalidException {
        String token = tokenService.refresh(tokenDTO.getToken(), tokenDTO.getUserUuid());
        return ResponseEntity.status(200).body(token);
    }

}
