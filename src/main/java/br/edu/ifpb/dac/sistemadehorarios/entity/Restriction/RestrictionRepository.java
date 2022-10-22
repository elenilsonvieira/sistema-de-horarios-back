package br.edu.ifpb.dac.sistemadehorarios.entity.Restriction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;

@Repository
public interface RestrictionRepository extends JpaRepository<RestrictionModel, String> {
	
	public RestrictionModel findByUuid(String uuid);
	public List<RestrictionModel> findByProfessorModel(ProfessorModel professorModel);
}
