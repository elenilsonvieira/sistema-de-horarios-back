package br.edu.ifpb.dac.sistemadehorarios.controller.interval;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.ShiftDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.ShiftInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.service.interval.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftService service;

    @PostMapping
    public ResponseEntity<ShiftDTO> create(@RequestBody ShiftModel shiftModel) throws ShiftInvalidException {

        boolean result = this.service.create(shiftModel);
        if(result) {
            return ResponseEntity.status(201).body(new ShiftDTO(shiftModel));
        }
        return ResponseEntity.status(400).body(null);

    }

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
