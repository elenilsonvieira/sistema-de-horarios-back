package br.edu.ifpb.dac.sistemadehorarios.repository;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonModel, String> {

    public LessonModel findByUuid(String uuid);
}
