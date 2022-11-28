package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "system_user")
public class UserModel implements UserDetails {

    @Id
    private String enrollment;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String roles;
    private Date create_at = new Date();
    @Column(updatable = true)
    private Date update_at;

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