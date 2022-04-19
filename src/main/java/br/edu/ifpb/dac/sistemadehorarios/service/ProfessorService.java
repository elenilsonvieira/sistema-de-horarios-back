package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public boolean create(ProfessorModel classModel) {
        try {
            this.repository.save(classModel);
            return true;
        }catch (Exception error){
        	System.err.println(error.getMessage());
            return false;
        }
    }

    public List<ProfessorModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
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

    public boolean delete(String uuid) {
        try {
            this.repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public ProfessorModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }
}
