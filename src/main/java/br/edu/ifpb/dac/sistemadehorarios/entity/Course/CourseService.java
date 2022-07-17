package br.edu.ifpb.dac.sistemadehorarios.entity.Course;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.templates.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends ServiceTemplate {
	
	@Autowired
	private CourseRepository repository;
	
	public boolean create(CourseModel courseModel) throws CourseInvalidException {

        try{
            return super.create(courseModel, this.repository);
        }catch (Exception error){
            throw new CourseInvalidException("Houve um problema para criar um Course. Erro: "+error.getMessage(), 400);
        }
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
