package br.edu.ifpb.dac.sistemadehorarios.entity.Profile;

import br.edu.ifpb.dac.sistemadehorarios.exception.ProfileInvalidException;

import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ProfileService extends ServiceTemplate {
    
    @Autowired
    private ProfileRepository repository;

    public ProfileModel create(ProfileModel profile) throws ProfileInvalidException {
        try{
            boolean create = super.create(profile, this.repository);
            if(create){
                return profile;
            }
            return null;
        }catch (Exception error) {
            throw new ProfileInvalidException("Houve um problema para criar um Profile. Error: " + error.getMessage(), 400);
        }
    }

    public List<ProfileModel> read() {
        return (List<ProfileModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public ProfileModel findByUuid(String uuid) {
        return (ProfileModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(ProfileModel profileModel, String uuid) {
        try {
            ProfileModel result = this.repository.findByUuid(uuid);

            String field = profileModel.getField()==null? result.getField() : profileModel.getField();
            Integer standard = profileModel.getStandard()==null? result.getStandard() : profileModel.getStandard();
            result.setField(field);
            result.setStandard(standard);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}
