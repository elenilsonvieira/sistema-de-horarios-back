package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.UserInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.service.LoginService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/user")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/{enrollment}")
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity create(@PathVariable String enrollment) throws UserInvalidException {
        UserModel userModel = userService.create(loginService.getSuapToken(), enrollment, null); // TODO Pass roles

        return userModel != null
                ? ResponseEntity.status(201).body(userModel)
                : ResponseEntity.status(400).body("Bad request");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<List<UserDTO>> read() {
        List<UserModel> result = this.userService.read();
        return ResponseEntity.status(200).body(UserDTO.convert(result));
    }

    @GetMapping("/findByEnrollment/{enrollment}")
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<UserDTO> findByUuid(@PathVariable("enrollment") String enrollment) {
        UserModel result = this.userService.findByUuid(enrollment);

        return result != null
                ? ResponseEntity.status(200).body(new UserDTO(result))
                : ResponseEntity.status(400).body(null);
    }

    @DeleteMapping("/{enrollment}")
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity delete(@PathVariable("enrollment") String enrollment) {
        return userService.delete(enrollment)
                ? ResponseEntity.status(200).body("OK")
                : ResponseEntity.status(400).body("Bad request");
    }

}
