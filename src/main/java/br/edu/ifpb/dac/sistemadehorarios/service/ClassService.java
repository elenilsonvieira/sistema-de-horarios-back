package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassRepository;
import br.edu.ifpb.dac.sistemadehorarios.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService extends ServiceAbstract{
	
    @Autowired
    private ClassRepository repository;
    
    @Autowired
    private CourseRepository courseRepository;
    

    public boolean create(ClassModel classModel, String uuid) {
        try {
        	CourseModel courseModel = this.courseRepository.findByUuid(uuid);
            if(courseModel == null){
                return false;
            }
        	classModel.setCourseModel(courseModel);
            return super.create(classModel, this.repository);
        }catch (Exception error){
            return false;
        }
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }
    public ClassModel findByUuid(String uuid) {
        return (ClassModel) super.findByUuid(uuid, this.repository);
    }
    public List<ClassModel> read() {
       return (List<ClassModel>) super.read(this.repository);
    }

    public boolean update(ClassModel classModel, String uuid) {
        try {
            ClassModel result = this.repository.findByUuid(uuid);

            String name = classModel.getName()==null? result.getName() : classModel.getName();

            result.setName(name);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}
