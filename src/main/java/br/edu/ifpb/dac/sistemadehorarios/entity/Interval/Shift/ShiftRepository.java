package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<ShiftModel, String> {
    
    public ShiftModel findByShiftEnum(ShiftEnum shift);
    public ShiftModel findByDisplayName(String displayName);
}
