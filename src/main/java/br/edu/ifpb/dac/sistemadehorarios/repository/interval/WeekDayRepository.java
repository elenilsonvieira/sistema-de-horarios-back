package br.edu.ifpb.dac.sistemadehorarios.repository.interval;

import br.edu.ifpb.dac.sistemadehorarios.model.interval.WeekDayModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayRepository extends JpaRepository<WeekDayModel, String> {

}
