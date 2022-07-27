package br.edu.ifpb.dac.sistemadehorarios.entity.Role;

import br.edu.ifpb.dac.sistemadehorarios.exception.RoleException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends ServiceTemplate {

    @Autowired
    private RoleReposiory repository;

    public void createDefaultValues() throws RoleException {
        for (RoleEnum roleEnum: RoleEnum.values()) {
            RoleModel role = this.findByName(roleEnum.name());
            if(role == null){
                role = new RoleModel();
                role.setName(roleEnum.name());
                this.create(role);
            }
        }
    }

    protected RoleModel create(RoleModel role) throws RoleException {
        try{
            boolean result = super.create(role, repository);
            if(!result){
                return null;
            }
            return role;
        }catch (Exception error){
             throw new RoleException("Houve um problema na criação da role. Error: "+ error.getMessage());
        }
    }

    public RoleModel findByName(String name){
        return repository.findByName(name);
    }

    public RoleModel findDefault(){
        return findByName(RoleEnum.USER.name());
    }
}
