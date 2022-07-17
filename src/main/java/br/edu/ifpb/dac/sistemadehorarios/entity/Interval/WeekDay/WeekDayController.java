package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.WeekDayDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.WeekDayInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekDay")
public class WeekDayController {

    @Autowired
    private WeekDayService service;
    @PostMapping
    public ResponseEntity<WeekDayDTO> create(@RequestBody WeekDayModel weekDayModel) throws WeekDayInvalidException {
        boolean result = this.service.create(weekDayModel);
        if(result) {
            return ResponseEntity.status(201).body(new WeekDayDTO(weekDayModel));
        }
        return ResponseEntity.status(400).body(null);

    }

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

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
        boolean result = this.service.delete(uuid);
        if (result) {
            return ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");
    }
}
