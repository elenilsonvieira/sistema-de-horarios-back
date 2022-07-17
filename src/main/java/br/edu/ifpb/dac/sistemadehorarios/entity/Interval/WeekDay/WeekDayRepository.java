package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayRepository extends JpaRepository<WeekDayModel, String> {

}
