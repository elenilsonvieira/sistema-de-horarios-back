package br.edu.ifpb.dac.sistemadehorarios.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleReposiory extends JpaRepository<RoleModel, String> {
    public RoleModel findByName(String name);
}
