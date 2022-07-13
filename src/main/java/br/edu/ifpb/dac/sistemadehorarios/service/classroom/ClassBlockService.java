package br.edu.ifpb.dac.sistemadehorarios.service.classroom;

import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassBlockInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassNameInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.classroom.ClassBlockRepository;
import br.edu.ifpb.dac.sistemadehorarios.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassBlockService extends ServiceTemplate {
    @Autowired
    private ClassBlockRepository repository;

    public boolean create(ClassBlockModel classBlockModel) throws ClassBlockInvalidException {
        try{
            return super.create(classBlockModel, this.repository);
        }catch (Exception error){
            throw new ClassBlockInvalidException("Houve um problema para criar um Gap. Erro: "+error.getMessage(), 400);
        }
    }

    public List<ClassBlockModel> read() {
        return (List<ClassBlockModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public ClassBlockModel findByUuid(String uuid) {
        return (ClassBlockModel) super.findByUuid(uuid, this.repository);
    }
}
