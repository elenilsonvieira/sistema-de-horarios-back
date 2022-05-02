package br.edu.ifpb.dac.sistemadehorarios.repository;

import br.edu.ifpb.dac.sistemadehorarios.model.CalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarModel, String> {
    public CalendarModel findByUuid(String uuid);
}
