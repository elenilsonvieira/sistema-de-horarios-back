package br.edu.ifpb.dac.sistemadehorarios.entity.Turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, String> {

    public TurmaModel findByUuid(String uuid);
}
