package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends ServiceAbstract{

    @Autowired
    private ProfessorRepository repository;

    public boolean create(ProfessorModel classModel) {
        return super.create(classModel, this.repository);
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
