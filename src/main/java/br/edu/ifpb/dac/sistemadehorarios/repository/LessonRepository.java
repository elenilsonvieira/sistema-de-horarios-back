package br.edu.ifpb.dac.sistemadehorarios.repository;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends JpaRepository<LessonModel, String> {

    public LessonModel findByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE classroom_uuid=:classroomUuid AND professor_uuid=:professorUuid AND interval_uuid=:intervalUuid AND corricular_component_uuid=:corricularComponentUuid AND turma_uuid=:turmaUuid")
    public LessonModel lessonEqualsValidation(
            String classroomUuid,
            String professorUuid,
            String intervalUuid,
            String corricularComponentUuid,
            String turmaUuid
            );

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE classroom_uuid=:classroomUuid AND interval_uuid=:intervalUuid")
    public LessonModel classroomAndIntervalValidation(
            String classroomUuid,
            String intervalUuid
            );
}
