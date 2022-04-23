package br.edu.ifpb.dac.sistemadehorarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GapRepository extends JpaRepository<GapModel, String>{
	
	public GapModel findByUuid(String uuid);

	@Query(nativeQuery = true, value = "SELECT * FROM gap WHERE day_of_week=:dayOfWeek AND shift=:shift AND interval_class=:interval")
	public GapModel findByDayAndInterval(String dayOfWeek, String shift ,int interval);

}
