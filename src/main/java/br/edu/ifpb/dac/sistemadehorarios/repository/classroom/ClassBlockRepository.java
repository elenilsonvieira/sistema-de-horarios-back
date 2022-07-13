package br.edu.ifpb.dac.sistemadehorarios.repository.classroom;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassBlockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassBlockRepository extends JpaRepository<ClassBlockModel, String> {
}
