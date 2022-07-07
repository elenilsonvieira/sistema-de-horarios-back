package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CurricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CurricularComponentModel;

import br.edu.ifpb.dac.sistemadehorarios.repository.CurricularComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurricularComponentService extends ServiceTemplate {
    @Autowired
    private CurricularComponentRepository repository;
    @Autowired
    private CourseService courseService;


    public CurricularComponentModel create(CurricularComponentDRO DRO) throws CurricularComponentInvalidException {
        try {
            CourseModel courseModel =  courseService.findByUuid(DRO.getCourseUuid());
            if(courseModel==null){
                throw new CurricularComponentInvalidException("O Curso não existe", 400);
            }
            CurricularComponentModel corricularComponent = new CurricularComponentModel();
            corricularComponent.setName(DRO.getName());
            corricularComponent.setWorkload(DRO.getWorkload());
            corricularComponent.setCourseUuid(courseModel);
            if(DRO.getUuid() != null){
                corricularComponent.setUuid(DRO.getUuid());
            }
            super.create(corricularComponent, this.repository);
            return corricularComponent;
        }catch (Exception error){
            throw new CurricularComponentInvalidException("Houve um problema para criar um Curricular Component. Erro: "+error.getMessage(), 400);
        }
    }

    public List<CurricularComponentModel> read() {
       return (List<CurricularComponentModel>) super.read(this.repository);
    }

    public boolean update(CurricularComponentModel curricularComponentModel, String uuid) {
        try {
            CurricularComponentModel result = this.repository.findByUuid(uuid);

            String name = curricularComponentModel.getName()==null? result.getName() : curricularComponentModel.getName();
            byte workload = curricularComponentModel.getWorkload()==0? result.getWorkload() : curricularComponentModel.getWorkload();
            result.setName(name);
            result.setWorkload(workload);

            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public boolean delete(String uuid) {
       return super.delete(uuid, this.repository);
    }

    public CurricularComponentModel findByUuid(String uuid) {
       return (CurricularComponentModel) super.findByUuid(uuid, this.repository);
    }
}
