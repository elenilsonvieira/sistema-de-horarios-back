package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekDay")
public class WeekDayController {

    @Autowired
    private WeekDayService service;

    @GetMapping
    public ResponseEntity <List<WeekDayDTO>> read(){
        List<WeekDayModel> result = this.service.read();
        return ResponseEntity.status(200).body(WeekDayDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<WeekDayDTO> findByUuid(@PathVariable("uuid") String uuid) {
        WeekDayModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new WeekDayDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/get-by-displayName/{displayName}")
    public ResponseEntity<WeekDayDTO> findByDisplayName(@PathVariable("displayName") String displayName) {
        WeekDayModel result = this.service.findByDisplayName(displayName);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new WeekDayDTO(result));
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
