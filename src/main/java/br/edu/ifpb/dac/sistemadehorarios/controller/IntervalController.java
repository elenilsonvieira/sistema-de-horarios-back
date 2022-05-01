package br.edu.ifpb.dac.sistemadehorarios.controller;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.middleware.IntervalMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.sistemadehorarios.DTO.IntervalDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.service.IntervalService;

@RestController
@RequestMapping("/interval")
public class IntervalController {
	
	@Autowired
	private IntervalService service;

	@Autowired
	private IntervalMiddleware middleware;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody IntervalModel interval){

		try{
			middleware.isValidInterval(interval);
			if(interval.getInterval() > 6){
				return ResponseEntity.status(400).body("Invalid interval. Needs a interval <= 6");
			}
			boolean result = this.service.create(interval);
			if(result) {
				return ResponseEntity.status(201).body(new IntervalDTO(interval));
			}
			return ResponseEntity.status(400).body(null);
		}catch (IntervalInvalidException error) {
			return ResponseEntity.status(400).body(error.getMessage());
		}
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
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
		boolean result = this.service.delete(uuid);
		if (result) {
			return ResponseEntity.status(200).body("OK");
		}
		return ResponseEntity.status(404).body("NOT OK");
	}

}
