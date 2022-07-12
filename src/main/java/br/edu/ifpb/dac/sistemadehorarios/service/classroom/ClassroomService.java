package br.edu.ifpb.dac.sistemadehorarios.service.classroom;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.DRO.ClassroomDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.classroom.ClassroomRepository;

@Service
public class ClassroomService extends ServiceTemplate {

	@Autowired
	private ClassroomRepository repository;

    @Autowired
    private ClassBlockService classBlockService;

    @Autowired
    private ClassNameService classNameService;

	public ClassroomModel create(ClassroomDRO classroomDRO) throws ClassroomInvalidException {
        try{
            ClassBlockModel classBlockModel = this.classBlockService.findByUuid(classroomDRO.getClassBlockUuid());
            ClassNameModel classNameModel = this.classNameService.findByUuid(classroomDRO.getClassNameUuid());

            if(classNameModel != null || classBlockModel != null) {
                ClassroomModel classroomModel = new ClassroomModel();
                classroomModel.setClassBlockModel(classBlockModel);
                classroomModel.setClassNameModel(classNameModel);
                boolean result = super.create(classroomModel, this.repository);
                return result ? classroomModel : null;
            }
            throw new ClassroomInvalidException("Houve um problema para criar uma Classroom. Algum valor inválido foi informado", 400);

        }catch (Exception error){
            throw new ClassroomInvalidException("Houve um problema para criar um Classroom. Erro: "+error.getMessage(), 400);
        }
    }
	
	public List<ClassroomModel> read() {
        return (List<ClassroomModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid,this.repository);
    }

    public ClassroomModel findByUuid(String uuid) {
        return (ClassroomModel) super.findByUuid(uuid, this.repository);
    }
	
	public boolean update(ClassroomModel classroom, String uuid) {
        try {
            ClassroomModel result = this.repository.findByUuid(uuid);
            ClassNameModel name = classroom.getClassNameModel()==null? result.getClassNameModel() : classroom.getClassNameModel();
            ClassBlockModel block = classroom.getClassBlockModel()==null? result.getClassBlockModel() : classroom.getClassBlockModel();

            result.setClassNameModel(name);
            result.setClassBlockModel(block);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

}
