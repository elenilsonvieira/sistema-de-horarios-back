package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<LessonModel, String> {

    public LessonModel findByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE classroom_uuid=:classroomUuid AND professor_uuid=:professorUuid AND corricular_component_uuid=:corricularComponentUuid AND turma_uuid=:turmaUuid and course_uuid=:courseUuid")
    public LessonModel lessonEqualsValidation(
            String classroomUuid,
            String professorUuid,
            String corricularComponentUuid,
            String turmaUuid,
            String courseUuid
            );

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE interval_uuid is null")
    public List<LessonModel> getWithoutInterval();
    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE interval_uuid is not null")
    public List<LessonModel> getWithInterval();

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE course_uuid=:courseModelUuid")
    public List<LessonModel> getByCourseModelFilter(String courseModelUuid);

    @Query(nativeQuery = true, value = "SELECT * FROM lesson WHERE professor_uuid=:professorModelUuid")
    public List<LessonModel> getByProfessorModelFilter(String professorModelUuid);

}
