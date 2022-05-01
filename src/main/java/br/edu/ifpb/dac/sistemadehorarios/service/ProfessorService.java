package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends ServiceTemplate {

    @Autowired
    private ProfessorRepository repository;
    @Autowired
    private CourseService courseService;

    public ProfessorModel create(ProfessorDRO DRO) {

        CourseModel courseModel =  courseService.findByUuid(DRO.getCourseUuid());
        if(courseModel==null){
            return null;
        }
        ProfessorModel professorModel = new ProfessorModel();
        professorModel.setName(DRO.getName());
        professorModel.setArea(DRO.getArea());
        professorModel.setCourseUuid(courseModel);
        if(DRO.getUuid() != null){
            professorModel.setUuid(DRO.getUuid());
        }
        super.create(professorModel, this.repository);
        return professorModel;
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
