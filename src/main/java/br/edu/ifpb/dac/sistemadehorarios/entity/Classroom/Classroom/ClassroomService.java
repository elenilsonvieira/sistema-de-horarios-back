package br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockService;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassroomService extends ServiceTemplate {

	@Autowired
	private ClassroomRepository repository;

    @Autowired
    private ClassBlockService classBlockService;


	public ClassroomModel create(ClassroomDRO classroomDRO) throws ClassroomInvalidException {
        try{
            ClassBlockModel classBlockModel = this.classBlockService.findByUuid(classroomDRO.getClassBlockUuid());
            
            if(classBlockModel != null) {
                ClassroomModel classroomModel = new ClassroomModel();
                
                classroomModel.setClassBlockModel(classBlockModel);
                classroomModel.setName(classroomDRO.getName());
                classroomModel.setCapacity(classroomDRO.getCapacity());

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

            String name = classroom.getName()==null? result.getName() : classroom.getName();
            Integer capacity = classroom.getCapacity()==null? result.getCapacity() : classroom.getCapacity();
            ClassBlockModel block = classroom.getClassBlockModel()==null? result.getClassBlockModel() : classroom.getClassBlockModel();

            result.setName(name);
            result.setCapacity(capacity);
            result.setClassBlockModel(block);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

}
