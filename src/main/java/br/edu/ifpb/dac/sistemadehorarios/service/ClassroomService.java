package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.CalendarInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.ClassroomInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassroomRepository;

@Service
public class ClassroomService extends ServiceTemplate {

	@Autowired
	private ClassroomRepository repository;


	
	public boolean create(ClassroomModel classroom) throws ClassroomInvalidException {
        try{
            return super.create(classroom, this.repository);
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
            String block = classroom.getBlock()==null? result.getBlock() : classroom.getBlock();
            int capacity = classroom.getCapacity()== 0 ? result.getCapacity(): classroom.getCapacity();

            result.setName(name);
            result.setBlock(block);
            result.setCapacity(capacity);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

}
