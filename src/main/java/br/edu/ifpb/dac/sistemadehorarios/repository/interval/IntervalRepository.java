package br.edu.ifpb.dac.sistemadehorarios.repository.interval;

import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<IntervalModel, String>{
	
	public IntervalModel findByUuid(String uuid);

	@Query(nativeQuery = true, value = "SELECT * FROM interval_time WHERE day_of_week=:dayOfWeek AND shift=:shift AND interval_class=:interval")
	public IntervalModel findByDayAndInterval(String dayOfWeek, String shift , String interval);

}
