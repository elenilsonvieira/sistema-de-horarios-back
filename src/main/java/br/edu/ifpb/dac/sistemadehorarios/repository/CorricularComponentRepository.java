package br.edu.ifpb.dac.sistemadehorarios.repository;

import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorricularComponentRepository extends JpaRepository<CorricularComponentModel, String> {
    public CorricularComponentModel findByUuid(String uuid);

}
