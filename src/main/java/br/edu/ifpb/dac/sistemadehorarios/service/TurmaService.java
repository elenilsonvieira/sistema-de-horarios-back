package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.DRO.TurmaDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.TurmaInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService extends ServiceTemplate {
	
    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CourseService courseService;

    public TurmaModel create(TurmaDRO DRO) throws TurmaInvalidException {
        try {
            CourseModel course = this.courseService.findByUuid(DRO.getCourseUuid());

            if(course == null){
                throw new TurmaInvalidException("O Curso não existe", 400);
            }

            TurmaModel turma = new TurmaModel();
            turma.setName(DRO.getName());
            turma.setCourseModel(course);
            super.create(turma, this.repository);
            return turma;
        }catch (Exception error){
            throw new TurmaInvalidException("Houve um problema para criar um Turma. Error: "+error.getMessage(), 400);
        }
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }
    public TurmaModel findByUuid(String uuid) {
        return (TurmaModel) super.findByUuid(uuid, this.repository);
    }
    public List<TurmaModel> read() {
       return (List<TurmaModel>) super.read(this.repository);
    }

    public boolean update(TurmaModel turmaModel, String uuid) {
        try {
            TurmaModel result = this.repository.findByUuid(uuid);

            String name = turmaModel.getName()==null? result.getName() : turmaModel.getName();

            result.setName(name);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}
