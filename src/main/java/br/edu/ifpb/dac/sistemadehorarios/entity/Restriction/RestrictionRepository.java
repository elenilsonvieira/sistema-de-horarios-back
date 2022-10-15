package br.edu.ifpb.dac.sistemadehorarios.entity.Restriction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionRepository extends JpaRepository<RestrictionModel, String> {
	
	public RestrictionModel findByUuid(String uuid);

}
