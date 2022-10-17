package br.edu.ifpb.dac.sistemadehorarios.entity.Restriction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.sistemadehorarios.DTO.RestrictionDTO;

@RestController
@RequestMapping("/restriction")
public class RestrictionController {

    @Autowired
    private RestrictionService service;

    @PostMapping
    public ResponseEntity<RestrictionDTO> create(@RequestBody RestrictionModel restrictionModel) throws Exception {
        RestrictionModel result = this.service.create(restrictionModel);
        if (result != null) {
            return ResponseEntity.status(201).body(new RestrictionDTO(restrictionModel));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity<List<RestrictionDTO>> read() {
        List<RestrictionModel> result = this.service.read();
        return ResponseEntity.status(200).body(RestrictionDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<RestrictionDTO> findByUuid(@PathVariable("uuid") String uuid) {
        RestrictionModel result = this.service.findByUuid(uuid);
        if (result != null) {
            return ResponseEntity.status(201).body(new RestrictionDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<RestrictionDTO> update(@RequestBody RestrictionModel restrictionModel,
            @PathVariable("uuid") String uuid) {
        if (this.service.update(restrictionModel, uuid)) {
            return ResponseEntity.status(200).body(new RestrictionDTO(restrictionModel));
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid) {
        if (this.service.delete(uuid)) {
            return ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");
    }

}
