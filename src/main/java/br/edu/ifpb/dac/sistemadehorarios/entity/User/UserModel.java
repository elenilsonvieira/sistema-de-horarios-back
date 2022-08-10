package br.edu.ifpb.dac.sistemadehorarios.entity.User;
import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name= "user_entity")
public class UserModel implements UserDetails{

    @Id
    private String uuid;
    private String name;
    @Column(unique = true)
    private String email;
    private String pass;
    private String roleEnum;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public UserModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.getPass();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}