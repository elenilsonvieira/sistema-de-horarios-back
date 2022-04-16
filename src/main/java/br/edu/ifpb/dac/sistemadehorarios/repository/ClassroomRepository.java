package br.edu.ifpb.dac.sistemadehorarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;

public interface ClassroomRepository extends JpaRepository<ClassroomModel, String>{
	
	public ClassroomModel findByUuid(String uuid);
    public ClassroomModel deleteByUuid(String uuid);
}
