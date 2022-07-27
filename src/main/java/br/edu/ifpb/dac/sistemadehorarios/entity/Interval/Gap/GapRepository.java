package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GapRepository extends JpaRepository<GapModel, String> {
    public GapModel findByGap(String gap);
}
