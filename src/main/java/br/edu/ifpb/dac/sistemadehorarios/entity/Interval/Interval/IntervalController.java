package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.IntervalDTO;

@RestController
@RequestMapping("/interval")
public class IntervalController {
	
	@Autowired
	private IntervalService service;

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody IntervalDRO intervalDRO) throws IntervalInvalidException {

		IntervalModel interval = this.service.create(intervalDRO);
		if(interval != null) {
			return ResponseEntity.status(201).body(new IntervalDTO(interval));
		}
		return ResponseEntity.status(400).body("Inválido");

	}
	
	@GetMapping
	public ResponseEntity <List<IntervalDTO>> read(){
		List<IntervalModel> result = this.service.read();
		return ResponseEntity.status(200).body(IntervalDTO.convert(result));
	}
	
	@GetMapping("/get-by-uuid/{uuid}")
	public ResponseEntity<IntervalDTO> findByUuid(@PathVariable("uuid") String uuid) {
        IntervalModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new IntervalDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }
	
	@DeleteMapping("/{uuid}/{lessonUuid}")
	public ResponseEntity<String> delete(@PathVariable("uuid") String uuid, @PathVariable("lessonUuid") String lessonUuid){
		boolean result = this.service.delete(uuid, lessonUuid);
		if (result) {
			return ResponseEntity.status(200).body("OK");
		}
		return ResponseEntity.status(404).body("NOT OK");
	}

}
