package br.edu.ifpb.dac.sistemadehorarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;

public interface GapRepository extends JpaRepository<GapModel, String>{
	
	public GapModel findByUuid(String uuid);
    public GapModel deleteByUuid(String uuid);

}
