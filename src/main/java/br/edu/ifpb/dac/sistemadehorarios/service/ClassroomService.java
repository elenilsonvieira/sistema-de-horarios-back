package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassroomRepository;

@Service
public class ClassroomService {
	
	@Autowired
	private ClassroomRepository repository;
	
	public boolean create(ClassroomModel classroom) {
        try {
            this.repository.save(classroom);
            return true;
        }catch (Exception error){
            return false;
        }
    }
	
	public List<ClassroomModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
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

    public boolean delete(String uuid) {
        try {
            this.repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public ClassroomModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }

}
