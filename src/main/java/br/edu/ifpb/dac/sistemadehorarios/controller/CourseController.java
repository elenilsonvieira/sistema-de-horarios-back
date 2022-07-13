package br.edu.ifpb.dac.sistemadehorarios.controller;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
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

import br.edu.ifpb.dac.sistemadehorarios.DTO.CourseDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@PostMapping
	public ResponseEntity<CourseDTO> create(@RequestBody CourseModel course) throws CourseInvalidException {
		boolean result = this.service.create(course);
		if(result) {
			return ResponseEntity.status(201).body(new CourseDTO(course));
		}
		return ResponseEntity.status(400).body(null);
	}
	
	@GetMapping
	public ResponseEntity <List<CourseDTO>> read(){
		List<CourseModel> result = this.service.read();
		return ResponseEntity.status(200).body(CourseDTO.convert(result));
	}
	
	@GetMapping("/get-by-uuid/{uuid}")
	public ResponseEntity<CourseDTO> findByUuid(@PathVariable("uuid") String uuid) {
		CourseModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new CourseDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }
	
	
	@PutMapping("/{uuid}")
	public ResponseEntity<CourseDTO> update(@RequestBody CourseModel course, @PathVariable("uuid") String uuid){
		boolean result = this.service.update(course, uuid);
		if (result) {
			return ResponseEntity.status(200).body(new CourseDTO(course));
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
