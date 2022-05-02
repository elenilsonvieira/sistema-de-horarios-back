package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
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
    private CorricularComponentService corricularComponentService;
    @Autowired
    private IntervalService intervalService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private CalendarService calendarService;

    public LessonModel create(LessonDRO lessonDRO){
        TurmaModel turmaModel = this.turmaService.findByUuid(lessonDRO.getTurmaUuid());
        CorricularComponentModel corricularComponentModel = this.corricularComponentService.findByUuid(lessonDRO.getCorricularComponentlUuid());
        IntervalModel intervalModel = this.intervalService.findByUuid(lessonDRO.getIntervalUuid());
        ProfessorModel professorModel = this.professorService.findByUuid(lessonDRO.getProfessorUuid());
        ClassroomModel classroomModel = this.classroomService.findByUuid(lessonDRO.getClassroomUuid());
        CalendarModel calendarModel = this.calendarService.findByUuid(lessonDRO.getCalendarUuid());

        if(turmaModel == null ||
            corricularComponentModel == null ||
            intervalModel == null ||
            professorModel == null ||
            classroomModel == null ||
            calendarModel == null){

            return null;
        }
        LessonModel lessonModel = new LessonModel();
        lessonModel.setTurmaModel(turmaModel);
        lessonModel.setCorricularComponentModel(corricularComponentModel);
        lessonModel.setIntervalModel(intervalModel);
        lessonModel.setProfessorModel(professorModel);
        lessonModel.setClassroomModel(classroomModel);
        lessonModel.setCalendarModel(calendarModel);

        boolean create = super.create(lessonModel, repository);
        if(create){
            return lessonModel;
        }
        return null;
    }

    public List<LessonModel> read(){
        return (List<LessonModel>) super.read(repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, repository);
    }

    public LessonModel findByUuid(String uuid) {
        return (LessonModel) super.findByUuid(uuid, repository);
    }
}
