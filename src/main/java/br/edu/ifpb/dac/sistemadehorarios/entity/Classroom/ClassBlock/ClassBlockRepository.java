package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassBlockRepository extends JpaRepository<ClassBlockModel, String> {
}
