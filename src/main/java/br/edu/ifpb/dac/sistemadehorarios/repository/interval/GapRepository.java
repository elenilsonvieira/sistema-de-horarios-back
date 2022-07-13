package br.edu.ifpb.dac.sistemadehorarios.repository.interval;


import br.edu.ifpb.dac.sistemadehorarios.model.interval.GapModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GapRepository extends JpaRepository<GapModel, String> {

}
