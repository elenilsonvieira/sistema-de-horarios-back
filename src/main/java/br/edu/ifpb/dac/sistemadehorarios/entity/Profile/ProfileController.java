package br.edu.ifpb.dac.sistemadehorarios.entity.Profile;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfileDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfileInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileModel profile) throws ProfileInvalidException {
        ProfileModel result = this.profileService.create(profile);
        if(result!=null) {
            return ResponseEntity.status(201).body(new ProfileDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> read(){
        List<ProfileModel> result = this.profileService.read();
        return ResponseEntity.status(200).body(ProfileDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ProfileDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ProfileModel result = this.profileService.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ProfileDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProfileDTO> update(@RequestBody ProfileModel profile, @PathVariable("uuid") String uuid) {
        boolean result = this.profileService.update(profile, uuid);
        if(result){
            return  ResponseEntity.status(200).body(new ProfileDTO(profile));
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
        boolean result = this.profileService.delete(uuid);
        if(result){
            return  ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");
    }

}
