package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name= "user_entity")
public class UserModel implements Serializable {

    @Id
    private String uuid;
    private String name;
    private String email;
    private String password;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;


    public UserModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }
}
