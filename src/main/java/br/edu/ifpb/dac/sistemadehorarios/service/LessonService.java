package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.*;
import br.edu.ifpb.dac.sistemadehorarios.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService extends ServiceAbstract{

    @Autowired
    private LessonRepository repository;

    @Autowired
    private ClassService classService;
    @Autowired
    private CorricularComponentService corricularComponentService;
    @Autowired
    private GapService gapService;
    @Autowired
    private ProfessorService professorService;

    public LessonModel create(LessonDRO lessonDRO) {
        ClassModel classModel = this.classService.findByUuid(lessonDRO.getClassModelUuid());
        CorricularComponentModel corricularComponentModel = this.corricularComponentService.findByUuid(lessonDRO.getCorricularComponentModelUuid());
        GapModel gapModel = this.gapService.findByUuid(lessonDRO.getGapModelUuid());
        ProfessorModel professorModel = this.professorService.findByUuid(lessonDRO.getProfessorModelUuid());

        if(classModel == null ||
            corricularComponentModel == null ||
            gapModel == null ||
            professorModel == null){

            return null;
        }
        LessonModel lessonModel = new LessonModel();
        lessonModel.setClassModel(classModel);
        lessonModel.setCorricularComponentModel(corricularComponentModel);
        lessonModel.setGapModel(gapModel);
        lessonModel.setProfessorModel(professorModel);

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
