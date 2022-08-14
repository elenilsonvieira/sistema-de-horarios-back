package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;


import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends ServiceTemplate {

    @Autowired
    private ProfessorRepository repository;

    public ProfessorModel create(ProfessorModel professor) throws ProfessorInvalidException {
        try{
            boolean create = super.create(professor, this.repository);
            if(create){
                return professor;
            }
            return null;
        }catch (Exception error) {
            throw new ProfessorInvalidException("Houve um problema para criar um Professor. Error: "+error.getMessage(), 400);
        }
    }

    public List<ProfessorModel> read() {
        return (List<ProfessorModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public ProfessorModel findByUuid(String uuid) {
        return (ProfessorModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(ProfessorModel professorModel, String uuid) {
        try {
            ProfessorModel result = this.repository.findByUuid(uuid);

            String name =professorModel.getName()==null? result.getName() : professorModel.getName();
            String area = professorModel.getArea()==null? result.getArea() : professorModel.getArea();
            result.setName(name);
            result.setArea(area);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}
