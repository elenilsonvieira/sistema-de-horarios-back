package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repository;
	
	public boolean create(CourseModel courseModel) {
        try {
            this.repository.save(courseModel);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public List<CourseModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
    }

    public boolean update(CourseModel courseModel, String uuid) {
        try {
        	CourseModel result = this.repository.findByUuid(uuid);

            String name = courseModel.getName()==null? result.getName() : courseModel.getName();
            result.setName(name);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public boolean delete(String uuid) {
        try {
            this.repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public CourseModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }
}
