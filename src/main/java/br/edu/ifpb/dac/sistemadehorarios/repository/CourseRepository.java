package br.edu.ifpb.dac.sistemadehorarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, String>{
	
	public CourseModel findByUuid(String uuid);
}
