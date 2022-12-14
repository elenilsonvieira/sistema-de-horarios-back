package br.edu.ifpb.dac.sistemadehorarios.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    public UserModel findByName(String name);
    public UserModel findByEnrollment(String enrollment);

    public boolean existsByEnrollment(String enrollment);
}
