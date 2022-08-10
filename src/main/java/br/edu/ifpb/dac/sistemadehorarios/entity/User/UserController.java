package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TokenDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.component.TokenComponent;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.utils.LoginDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private TokenComponent tokenComponent;
    private final UserService service;
    @Value("${secret.key}")
    private String secretKey;

    public UserController(UserService service) {
        this.service = service;
        this.tokenComponent = new TokenComponent();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDRO loginDRO){
        String email = loginDRO.getEmail();
        String password = loginDRO.getPass();
        UserModel userModel = service.login(email, password);

        if(userModel != null){
            String token = this.tokenComponent.generate(userModel);
            UserDTO userDTO = new UserDTO(userModel);
            userDTO.setToken(token);
            return ResponseEntity.status(202).body(userDTO);
        }

        return ResponseEntity.status(404).body("Login não econtrado");
    }
    @PostMapping()
    public ResponseEntity create(@RequestHeader("secretKey") String secretKey, @RequestBody UserModel user) throws UserInvalidException {

        if(secretKey.equals(this.secretKey)){
            UserModel userModel =  this.service.create(user);
            if(userModel != null){
                return ResponseEntity.status(201).body(new UserDTO(userModel));
            }
            return ResponseEntity.status(400).body("Bad request");
        }
        return ResponseEntity.status(403).body("Usuário identificado mas não autorizado");

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
    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@RequestHeader("secretKey") String secretKey, @PathVariable("uuid") String uuid){
        if(secretKey.equals(this.secretKey)){
            boolean result = this.service.delete(uuid);
            if (result) {
                return ResponseEntity.status(200).body("OK");
            }
            return ResponseEntity.status(404).body("NOT OK");
        }
        return ResponseEntity.status(403).body("Usuário identificado mas não autorizado");
    }
}
