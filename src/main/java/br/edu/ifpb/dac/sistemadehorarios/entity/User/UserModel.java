package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.uuid.Generators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Entity(name = "system_user")
public class UserModel implements UserDetails {

    @Id
    private String uuid;
    @Column(nullable = false)
    private String enrollment;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String roles;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

    public UserModel() {
        this.uuid= Generators.randomBasedGenerator().generate().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<String> authority = new ArrayList<>(Arrays.asList(roles.split(",")));

        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUsername() {
        return this.getEnrollment();
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