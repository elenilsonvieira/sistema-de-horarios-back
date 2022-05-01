package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CorricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;

import br.edu.ifpb.dac.sistemadehorarios.repository.CorricularComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CorricularComponentService extends ServiceTemplate {
    @Autowired
    private CorricularComponentRepository repository;
    @Autowired
    private CourseService courseService;


    public CorricularComponentModel create(CorricularComponentDRO DRO) {
        try {
            CourseModel courseModel =  courseService.findByUuid(DRO.getCourseUuid());
            if(courseModel==null){
                return null;
            }
            CorricularComponentModel corricularComponent = new CorricularComponentModel();
            corricularComponent.setName(DRO.getName());
            corricularComponent.setWorkload(DRO.getWorkload());
            corricularComponent.setCourseUuid(courseModel);
            if(DRO.getUuid() != null){
                corricularComponent.setUuid(DRO.getUuid());
            }
            super.create(corricularComponent, this.repository);
            return corricularComponent;
        }catch (Exception error){
            return null;
        }
    }

    public List<CorricularComponentModel> read() {
       return (List<CorricularComponentModel>) super.read(this.repository);
    }

    public boolean update(CorricularComponentModel corricularComponentModel, String uuid) {
        try {
            CorricularComponentModel result = this.repository.findByUuid(uuid);

            String name = corricularComponentModel.getName()==null? result.getName() : corricularComponentModel.getName();
            byte workload = corricularComponentModel.getWorkload()==0? result.getWorkload() : corricularComponentModel.getWorkload();
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

    public CorricularComponentModel findByUuid(String uuid) {
       return (CorricularComponentModel) super.findByUuid(uuid, this.repository);
    }
}
