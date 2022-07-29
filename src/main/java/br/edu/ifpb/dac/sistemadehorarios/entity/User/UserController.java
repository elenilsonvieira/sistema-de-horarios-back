package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TokenDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.component.TokenComponent;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.security.AuthenticationSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final TokenComponent tokenComponent;
    private final AuthenticationSecurity authenticationSecurity;

    public UserController(UserService service, TokenComponent tokenComponent, AuthenticationSecurity authenticationSecurity) {
        this.service = service;
        this.tokenComponent = tokenComponent;
        this.authenticationSecurity = authenticationSecurity;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDRO dro){
        try{
            String token  = authenticationSecurity.login(dro.getEmail(), dro.getPassword());
            UserModel entity = service.findByEmail(dro.getEmail());
            UserDTO userDTO = new UserDTO(entity);
            TokenDTO tokenDTO = new TokenDTO(token, userDTO);
            return ResponseEntity.status(200).body(tokenDTO);
        }catch (Exception error){
            return ResponseEntity.status(401).body(error.getMessage());
        }
    }

    @PostMapping("/validadeToken")
    public ResponseEntity isTokenValid(@RequestBody TokenDTO tokenDTO){
        try{
            boolean isTokenValid = tokenComponent.isValid(tokenDTO.getToken());
            return ResponseEntity.status(isTokenValid ? 200: 403).body(isTokenValid);
        }catch (Exception error){
            return ResponseEntity.status(401).body(error.getMessage());
        }
    }
}
