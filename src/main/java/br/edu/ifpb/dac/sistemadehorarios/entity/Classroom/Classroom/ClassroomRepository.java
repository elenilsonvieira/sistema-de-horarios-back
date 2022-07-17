package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomModel, String>{
	
	public ClassroomModel findByUuid(String uuid);

	@Query(nativeQuery = true, value = "SELECT * FROM classroom WHERE name=:name AND block=:block")
	public ClassroomModel classroomEqualsValidation(
			String name,
			String block
	);
}
