package br.edu.ifpb.dac.sistemadehorarios.entity.Calendar;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CalendarDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.CalendarInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService service;

    @PostMapping
    public ResponseEntity<CalendarDTO> create(@RequestBody CalendarModel calendar) throws CalendarInvalidException {

        boolean result = this.service.create(calendar);
        if(result) {
            return ResponseEntity.status(201).body(new CalendarDTO(calendar));
        }
        return ResponseEntity.status(400).body(null);

    }

    @GetMapping
    public ResponseEntity <List<CalendarDTO>> read(){
        List<CalendarModel> result = this.service.read();
        return ResponseEntity.status(200).body(CalendarDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<CalendarDTO> findByUuid(@PathVariable("uuid") String uuid) throws CalendarInvalidException {
        CalendarModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new CalendarDTO(result));
        }
        throw new CalendarInvalidException("Calendar não encontrado", 404);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CalendarDTO> update(@RequestBody CalendarModel calendar, @PathVariable("uuid") String uuid){
        boolean result = this.service.update(calendar, uuid);
        if (result) {
            return ResponseEntity.status(200).body(new CalendarDTO(calendar));
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
