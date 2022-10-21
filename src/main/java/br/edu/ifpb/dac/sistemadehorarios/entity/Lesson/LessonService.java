package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters.*;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService extends ServiceTemplate {

    @Autowired
    private LessonRepository repository;
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private CurricularComponentService curricularComponentService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private CourseService courseService;

    public LessonModel create(LessonDRO lessonDRO) throws LessonInvalidException {
        try {
            TurmaModel turmaModel = this.turmaService.findByUuid(lessonDRO.getTurmaUuid());
            CurricularComponentModel curricularComponentModel = this.curricularComponentService.findByUuid(lessonDRO.getCurricularComponentUuid());
            ProfessorModel professorModel = this.professorService.findByUuid(lessonDRO.getProfessorUuid());
            ClassroomModel classroomModel = this.classroomService.findByUuid(lessonDRO.getClassroomUuid());
            CalendarModel calendarModel = this.calendarService.findByUuid(lessonDRO.getCalendarUuid());
            CourseModel courseModel = this.courseService.findByUuid(lessonDRO.getCourseUuid());

            if (turmaModel == null ||
                    curricularComponentModel == null ||
                    professorModel == null ||
                    classroomModel == null ||
                    calendarModel == null ||
                    courseModel == null) {

                throw new LessonInvalidException("Um dos campos informados não existe", 400);
            }
            LessonModel lessonModel = new LessonModel();
            lessonModel.setTurmaModel(turmaModel);
            lessonModel.setCurricularComponentModel(curricularComponentModel);
            lessonModel.setProfessorModel(professorModel);
            lessonModel.setClassroomModel(classroomModel);
            lessonModel.setCalendarModel(calendarModel);
            lessonModel.setCourseModel(courseModel);

            if (super.create(lessonModel, repository))
                return lessonModel;

        } catch (Exception error) {
            throw new LessonInvalidException("Houve um problema para criar um Lesson. Error: " + error.getMessage(),
                    400);
        }
        return null;
    }

    public List<LessonModel> read() {
        return (List<LessonModel>) super.read(repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, repository);
    }

    public LessonModel findByUuid(String uuid) {
        return (LessonModel) super.findByUuid(uuid, repository);
    }

    public List<LessonModel> getWithoutInterval() {
        return this.repository.getWithoutInterval();
    }

    public List<LessonModel> getWithInterval() {
        return this.repository.getWithInterval();
    }

    public List<LessonModel> getByCourseByBlockAndClass(String... filtersList) {

        List<Filter> filters = new ArrayList<Filter>();

        filters.add(new FilterByCourse(filtersList[0], this.repository));
        filters.add(new FilterByProfessor(filtersList[1], this.repository));
        filters.add(new FilterByBlock(filtersList[2]));
        filters.add(new FilterByClassName(filtersList[3]));

        List<LessonModel> list = this.read();
        return Filter.getResult(filters, list);
    }

    public LessonModel update(LessonModel lessonModel, String uuid) {
        try {
            LessonModel result = this.repository.findByUuid(uuid);

            TurmaModel turmaModel = lessonModel.getTurmaModel() == null
                    ? result.getTurmaModel()
                    : lessonModel.getTurmaModel();
            CurricularComponentModel curricularComponentModel = lessonModel.getCurricularComponentModel() == null
                    ? result.getCurricularComponentModel()
                    : lessonModel.getCurricularComponentModel();
            ProfessorModel professorModel = lessonModel.getProfessorModel() == null
                    ? result.getProfessorModel()
                    : lessonModel.getProfessorModel();
            ClassroomModel classroomModel = lessonModel.getClassroomModel() == null
                    ? result.getClassroomModel()
                    : lessonModel.getClassroomModel();
            CalendarModel calendarModel = lessonModel.getCalendarModel() == null
                    ? result.getCalendarModel()
                    : lessonModel.getCalendarModel();
            CourseModel courseModel = lessonModel.getCourseModel() == null
                    ? result.getCourseModel()
                    : lessonModel.getCourseModel();

            result.setTurmaModel(turmaModel);
            result.setCurricularComponentModel(curricularComponentModel);
            result.setProfessorModel(professorModel);
            result.setClassroomModel(classroomModel);
            result.setCalendarModel(calendarModel);
            result.setCourseModel(courseModel);

            if(super.update(result, this.repository))
                return result;
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
