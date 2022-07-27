package br.edu.ifpb.dac.sistemadehorarios.entity.Role;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "role")
public class RoleModel implements GrantedAuthority {

    @Id
    private String uuid;
    private String name;


    public RoleModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
