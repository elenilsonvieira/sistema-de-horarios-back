package br.edu.ifpb.dac.sistemadehorarios.repository;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<LessonModel, String> {

    public LessonModel findByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE classroom_uuid=:classroomUuid AND professor_uuid=:professorUuid AND corricular_component_uuid=:corricularComponentUuid AND turma_uuid=:turmaUuid")
    public LessonModel lessonEqualsValidation(
            String classroomUuid,
            String professorUuid,
            String corricularComponentUuid,
            String turmaUuid
            );

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE interval_uuid is null")
    public List<LessonModel> getWithoutInterval();
    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE interval_uuid is not null")
    public List<LessonModel> getWithInterval();
}
