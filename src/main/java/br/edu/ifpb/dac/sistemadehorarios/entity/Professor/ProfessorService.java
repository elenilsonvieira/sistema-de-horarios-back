package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;


import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileRepository;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService extends ServiceTemplate {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    public ProfessorModel create(ProfessorDRO professor) throws ProfessorInvalidException {
        try{
            ProfileModel profile = profileRepository.findByUuid(professor.getProfileUuid());
            
            if(profile != null){
                ProfessorModel professorModel = new ProfessorModel();

                professorModel.setName(professor.getName());
                professorModel.setProfileModel(profile);

                boolean result = super.create(professorModel, this.repository);
                return result ? professorModel : null;
            }
            throw new ProfessorInvalidException("Houve um problema para criar uma Classroom. Algum valor inválido foi informado", 400);

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

            String name = professorModel.getName()==null? result.getName() : professorModel.getName();
            ProfileModel profile = professorModel.getProfileModel()==null? result.getProfileModel() : professorModel.getProfileModel();
            result.setName(name);
            result.setProfileModel(profile);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}
