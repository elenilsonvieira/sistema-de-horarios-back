package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassNameRepository extends JpaRepository<ClassNameModel, String> {
}
