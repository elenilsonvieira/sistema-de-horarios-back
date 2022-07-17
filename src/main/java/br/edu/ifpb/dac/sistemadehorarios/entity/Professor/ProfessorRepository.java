package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, String> {

    public ProfessorModel findByUuid(String uuid);
}
