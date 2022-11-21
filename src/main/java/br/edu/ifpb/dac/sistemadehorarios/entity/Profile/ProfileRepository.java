package br.edu.ifpb.dac.sistemadehorarios.entity.Profile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProfileRepository extends JpaRepository<ProfileModel, String> {

    public ProfileModel findByUuid(String uuid);
}
