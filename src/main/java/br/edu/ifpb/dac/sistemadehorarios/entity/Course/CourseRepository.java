package br.edu.ifpb.dac.sistemadehorarios.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, String>{
	
	public CourseModel findByUuid(String uuid);
}
