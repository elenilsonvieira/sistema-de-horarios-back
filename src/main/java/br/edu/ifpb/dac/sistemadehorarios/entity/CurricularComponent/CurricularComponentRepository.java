package br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurricularComponentRepository extends JpaRepository<CurricularComponentModel, String> {
    public CurricularComponentModel findByUuid(String uuid);

}
