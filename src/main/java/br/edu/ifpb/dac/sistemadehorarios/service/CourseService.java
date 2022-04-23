package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.CourseRepository;

@Service
public class CourseService extends ServiceAbstract{
	
	@Autowired
	private CourseRepository repository;
	
	public boolean create(CourseModel courseModel) {
       return super.create(courseModel, this.repository);
    }

    public List<CourseModel> read() {
       return (List<CourseModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public CourseModel findByUuid(String uuid) {
        return (CourseModel) super.findByUuid(uuid, this.repository);
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

}
