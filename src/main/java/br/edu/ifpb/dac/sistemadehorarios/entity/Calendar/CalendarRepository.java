package br.edu.ifpb.dac.sistemadehorarios.entity.Calendar;

import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarModel, String> {
    public CalendarModel findByUuid(String uuid);
}
