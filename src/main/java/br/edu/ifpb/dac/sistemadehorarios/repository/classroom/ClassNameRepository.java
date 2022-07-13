package br.edu.ifpb.dac.sistemadehorarios.repository.classroom;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassNameRepository extends JpaRepository<ClassNameModel, String> {
}
