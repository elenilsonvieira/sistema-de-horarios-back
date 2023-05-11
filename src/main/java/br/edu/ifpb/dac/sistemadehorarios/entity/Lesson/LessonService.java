package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters.*;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionService;
import br.edu.ifpb.dac.sistemadehorarios.DTO.LessonDTO;
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

import java.util.*;

@Service
public class LessonService extends ServiceTemplate {

    @Autowired
    private LessonRepository repository;
    @Autowired
    private TurmaService turmaService;
    @Autowired
    private CurricularComponentService curricularComponentService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RestrictionService restrictionService;
    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private IntervalService intervalService;

    @Autowired
    private IntervalRepository intervalRepository;


    public LessonModel create(LessonDRO lessonDRO) throws LessonInvalidException {
        try {
            CurricularComponentModel curricularComponentModel = this.curricularComponentService.findByUuid(lessonDRO.getCurricularComponentUuid());
            ClassroomModel classroomModel = this.classroomService.findByUuid(lessonDRO.getClassroomUuid());
            CalendarModel calendarModel = this.calendarService.findByUuid(lessonDRO.getCalendarUuid());
            CourseModel courseModel = this.courseService.findByUuid(lessonDRO.getCourseUuid());
            ProfessorModel professorModel = this.professorService.findByUuid(lessonDRO.getProfessorUuid());

            if (curricularComponentModel == null ||
                    classroomModel == null ||
                    calendarModel == null ||
                    courseModel == null || professorModel == null) {

                throw new LessonInvalidException("Um dos campos informados não existe", 400);
            }
            LessonModel lessonModel = new LessonModel();
            lessonModel.setTurmaModel(this.turmaService.findByUuid("período padrão na criação"));
            lessonModel.setCurricularComponentModel(curricularComponentModel);
            lessonModel.setClassroomModel(classroomModel);
            lessonModel.setCalendarModel(calendarModel);
            lessonModel.setCourseModel(courseModel);
            lessonModel.setProfessorModel(professorModel);

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

    public LessonDTO update(LessonDRO lessonDRO, String uuid) {
        System.out.println(lessonDRO.toString());
        try {
            LessonModel result = this.repository.findByUuid(uuid);
            System.out.println(lessonDRO);

            TurmaModel turmaModel = lessonDRO.getTurmaUuid() == null
                    ? result.getTurmaModel()
                    : this.turmaService.findByUuid(lessonDRO.getTurmaUuid());

            System.out.println(turmaModel);

            CurricularComponentModel curricularComponentModel = lessonDRO.getCurricularComponentUuid() == null
                    ? result.getCurricularComponentModel()
                    : this.curricularComponentService.findByUuid(lessonDRO.getCurricularComponentUuid());

            System.out.println(curricularComponentModel);

            ProfessorModel professorModel = lessonDRO.getProfessorUuid() == null
                    ? result.getProfessorModel()
                    : this.professorService.findByUuid(lessonDRO.getProfessorUuid());

            System.out.println(professorModel);

            IntervalModel intervalModel = lessonDRO.getIntervalUuid() == null
                    ? result.getIntervalModel()
                    : this.intervalService.findByUuid(lessonDRO.getIntervalUuid());

            System.out.println(intervalModel);

            ClassroomModel classroomModel = lessonDRO.getClassroomUuid() == null
                    ? result.getClassroomModel()
                    : this.classroomService.findByUuid(lessonDRO.getClassroomUuid());

            System.out.println(classroomModel);

            CalendarModel calendarModel = lessonDRO.getCalendarUuid() == null
                    ? result.getCalendarModel()
                    : this.calendarService.findByUuid(lessonDRO.getCalendarUuid());

            System.out.println(classroomModel);

            CourseModel courseModel = lessonDRO.getCourseUuid() == null
                    ? result.getCourseModel()
                    : this.courseService.findByUuid(lessonDRO.getCourseUuid());

            System.out.println(courseModel);

            intervalModel = intervalRepository.findByUuid(intervalModel.getUuid());
            List<RestrictionModel> restrictions = null;

            System.out.println("Interval tá com problema?");

            if(intervalModel != null && professorModel != null) {
                System.out.println("Null eles?");
            	restrictions = restrictionService.findByProfessorModel(professorModel);
                boolean check = true;
            	for(RestrictionModel restrictionModel: restrictions) {
            		if(restrictionModel.getWeekDayModel().getUuid().equals(intervalModel.getWeekDayModel().getUuid())) {
                		if(restrictionModel.getShiftModel().getUuid().equals(intervalModel.getShiftModel().getUuid()) 
                			||restrictionModel.getShiftModel().getUuid().equals(shiftService.findByShiftEnum(ShiftEnum.ALL_DAY).getUuid())) {
                            result.setProfessorModel(professorModel);
                            result.setIntervalModel(intervalModel);
                            check = false;
                		}
                	}        		
            	}

            	if(check) {
            		throw new LessonInvalidException("Professor não pode ser escalado para este horario", 400);
            	}

            } else if(intervalModel != null) {
                System.out.println("Só o interval");
            	result.setIntervalModel(intervalModel);
            } else if(professorModel != null) {
                System.out.println("Só o professor");
            	result.setProfessorModel(professorModel);
            }

            System.out.println("Chegou aqui, parceiro");

            result.setTurmaModel(turmaModel);
            result.setCurricularComponentModel(curricularComponentModel);
            result.setClassroomModel(classroomModel);
            result.setCalendarModel(calendarModel);
            result.setCourseModel(courseModel);

            result = repository.save(result);

            LessonDTO resultDTO = new LessonDTO(result);
            if (intervalModel != null && professorModel != null && !validateExtremeHours(result, restrictions)) {
                resultDTO.setTipMessage("Este horario não está bom para o professor: " + result.getProfessorModel().getName());
            }

            return resultDTO;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean validateExtremeHours(LessonModel newLesson, List<RestrictionModel> restrictions) {
        List<LessonModel> result = repository.getByProfessorModelFilter(newLesson.getProfessorModel().getUuid());
        Collections.sort(restrictions);

        SortedSet<LessonModel> lessons = new TreeSet<>();
        for (LessonModel lesson : result) {
            if (lesson.getIntervalModel() != null && !lesson.getUuid().equals(newLesson.getUuid())) {
                lessons.add(lesson);
            }
        }
        lessons.add(newLesson);

        if (lessons.size() > 1) {
            LessonModel first = lessons.first();
            LessonModel last = lessons.last();
            RestrictionModel firstRestriction = restrictions.get(0);
            RestrictionModel lastRestriction = restrictions.get(restrictions.size() -1);

            if ((newLesson == first) || newLesson == last) {
                boolean firstOnMorningShift = first.getIntervalModel().getShiftModel()
                        .compareTo(shiftService.findByShiftEnum(ShiftEnum.MORNING)) == 0;
                boolean lastOnNightShift = last.getIntervalModel().getShiftModel()
                        .compareTo(shiftService.findByShiftEnum(ShiftEnum.NIGHT)) == 0;
                
                boolean firstRestrictionOnFirstLesson = firstRestriction.getWeekDayModel()
                        .compareTo(first.getIntervalModel().getWeekDayModel()) == 0;
                boolean lastRestrictionOnLastLesson = lastRestriction.getWeekDayModel()
                        .compareTo(last.getIntervalModel().getWeekDayModel()) == 0;

                if (firstOnMorningShift && lastOnNightShift && (lastRestrictionOnLastLesson || firstRestrictionOnFirstLesson)) {
                    return false;
                }
            }
        }
        return true;
    }
}
