package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import java.time.DayOfWeek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayRepository extends JpaRepository<WeekDayModel, String> {

    public WeekDayModel findByDayOfWeek(DayOfWeek dayOfWeek);
    public WeekDayModel findByDisplayName(String displayName);

}
