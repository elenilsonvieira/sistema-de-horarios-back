package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.*;
import br.edu.ifpb.dac.sistemadehorarios.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public LessonModel create(LessonDRO lessonDRO) throws LessonInvalidException {
        try{
            TurmaModel turmaModel = this.turmaService.findByUuid(lessonDRO.getTurmaUuid());
            CurricularComponentModel curricularComponentModel = this.curricularComponentService.findByUuid(lessonDRO.getCurricularComponentUuid());
            ProfessorModel professorModel = this.professorService.findByUuid(lessonDRO.getProfessorUuid());
            ClassroomModel classroomModel = this.classroomService.findByUuid(lessonDRO.getClassroomUuid());
            CalendarModel calendarModel = this.calendarService.findByUuid(lessonDRO.getCalendarUuid());

            if(turmaModel == null ||
                    curricularComponentModel == null ||
                    professorModel == null ||
                    classroomModel == null ||
                    calendarModel == null){

                throw new LessonInvalidException("Um dos campos informados não existe", 400);
            }
            LessonModel lessonModel = new LessonModel();
            lessonModel.setTurmaModel(turmaModel);
            lessonModel.setCorricularComponentModel(curricularComponentModel);
            lessonModel.setProfessorModel(professorModel);
            lessonModel.setClassroomModel(classroomModel);
            lessonModel.setCalendarModel(calendarModel);

            boolean create = super.create(lessonModel, repository);
            if(create){
                return lessonModel;
            }

        }catch (Exception error){
            throw new LessonInvalidException("Houve um problema para criar um Lesson. Error: "+error.getMessage(), 400);

        }
        return null;
    }

    public List<LessonModel> read(){
        return (List<LessonModel>) super.read(repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, repository);
    }

    public List<LessonModel> getWithoutInterval(){
        return this.repository.getWithoutInterval();
    }
    public List<LessonModel> getWithInterval(){
        return this.repository.getWithInterval();
    }

    public LessonModel findByUuid(String uuid) {
        return (LessonModel) super.findByUuid(uuid, repository);
    }
}
