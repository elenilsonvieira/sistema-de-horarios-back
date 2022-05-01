package br.edu.ifpb.dac.sistemadehorarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
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
