package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository repository;

    public boolean create(ClassModel classModel) {
        try {
            this.repository.save(classModel);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public List<ClassModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
    }

    public boolean update(ClassModel classModel, String uuid) {
        try {
            ClassModel result = this.repository.findByUuid(uuid);

            String name = classModel.getName()==null? result.getName() : classModel.getName();
            String course = classModel.getCourse()==null? result.getCourse() : classModel.getCourse();

            result.setName(name);
            result.setCourse(course);
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

    public ClassModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }
}
