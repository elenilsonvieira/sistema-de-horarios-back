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

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
    private IntervalRepository intervalRepository;
    @Autowired
    private GapService gapService;

    public LessonModel create(LessonDRO lessonDRO) throws LessonInvalidException {
        try {
            TurmaModel turmaModel = this.turmaService.findByUuid(lessonDRO.getTurmaUuid());
            CurricularComponentModel curricularComponentModel = this.curricularComponentService.findByUuid(lessonDRO.getCurricularComponentUuid());
            ClassroomModel classroomModel = this.classroomService.findByUuid(lessonDRO.getClassroomUuid());
            CalendarModel calendarModel = this.calendarService.findByUuid(lessonDRO.getCalendarUuid());
            CourseModel courseModel = this.courseService.findByUuid(lessonDRO.getCourseUuid());

            if (turmaModel == null ||
                    curricularComponentModel == null ||
                    classroomModel == null ||
                    calendarModel == null ||
                    courseModel == null) {

                throw new LessonInvalidException("Um dos campos informados não existe", 400);
            }
            LessonModel lessonModel = new LessonModel();
            lessonModel.setTurmaModel(turmaModel);
            lessonModel.setCurricularComponentModel(curricularComponentModel);
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

    public LessonDTO update(LessonModel lessonModel, String uuid) {
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
            IntervalModel intervalModel = lessonModel.getIntervalModel() == null
                    ? result.getIntervalModel()
                    : lessonModel.getIntervalModel();
            ClassroomModel classroomModel = lessonModel.getClassroomModel() == null
                    ? result.getClassroomModel()
                    : lessonModel.getClassroomModel();
            CalendarModel calendarModel = lessonModel.getCalendarModel() == null
                    ? result.getCalendarModel()
                    : lessonModel.getCalendarModel();
            CourseModel courseModel = lessonModel.getCourseModel() == null
                    ? result.getCourseModel()
                    : lessonModel.getCourseModel();

            intervalModel = intervalRepository.findByUuid(intervalModel.getUuid());

            if(intervalModel != null && professorModel != null) {
            	List<RestrictionModel> restrictions = restrictionService.findByProfessorModel(professorModel);
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
            }else if(intervalModel != null) {
            	result.setIntervalModel(intervalModel);
            }else if(professorModel != null) {
            	result.setProfessorModel(professorModel);
            }

            result.setTurmaModel(turmaModel);
            result.setCurricularComponentModel(curricularComponentModel);
            result.setClassroomModel(classroomModel);
            result.setCalendarModel(calendarModel);
            result.setCourseModel(courseModel);
            if(super.update(result, this.repository)){
                LessonDTO resultDTO = new LessonDTO(result);
                if(intervalModel != null && professorModel != null) {
                    if(!validateExtremeHours(result)){
                        resultDTO.setTipMessage("Este horario não está bom para o professor:" +result.getProfessorModel().getName());
                    }
                }
                return resultDTO;
            }  
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private boolean validateExtremeHours (LessonModel newLesson){
        List<LessonModel> result = repository.getByProfessorModelFilter(newLesson.getProfessorModel().getUuid());
        SortedSet<LessonModel> lessons = new TreeSet<>();
        for(LessonModel lesson: result){
            if(lesson.getIntervalModel() != null && !lesson.getUuid().equals(newLesson.getUuid())){
                lessons.add(lesson);
            }
        }
        lessons.add(newLesson);
        if(lessons.size() > 1){
            if((newLesson.getUuid().equals(lessons.first().getUuid()) 
            || newLesson.getUuid().equals(lessons.last().getUuid()))){
                if((lessons.first().getIntervalModel().getShiftModel().compareTo(shiftService.findByShiftEnum(ShiftEnum.MORNING)) == 0) 
                 && (lessons.last().getIntervalModel().getShiftModel().compareTo(shiftService.findByShiftEnum(ShiftEnum.NIGHT)) != 0)){
                    return true;
                } else if((lessons.first().getIntervalModel().getShiftModel().compareTo(shiftService.findByShiftEnum(ShiftEnum.MORNING)) != 0) 
                && (lessons.last().getIntervalModel().getShiftModel().compareTo(shiftService.findByShiftEnum(ShiftEnum.MORNING)) != 0)){
                    return true;
                }
                return false;
            }
        }
        return true;
    }
}
