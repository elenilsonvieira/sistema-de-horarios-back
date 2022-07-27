package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.ShiftDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftService service;


    @GetMapping
    public ResponseEntity <List<ShiftDTO>> read(){
        List<ShiftModel> result = this.service.read();
        return ResponseEntity.status(200).body(ShiftDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ShiftDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ShiftModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ShiftDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
        boolean result = this.service.delete(uuid);
        if (result) {
            return ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");
    }
}