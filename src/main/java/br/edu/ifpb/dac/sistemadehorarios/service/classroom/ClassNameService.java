package br.edu.ifpb.dac.sistemadehorarios.service.classroom;

import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassNameInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.classroom.ClassNameRepository;
import br.edu.ifpb.dac.sistemadehorarios.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassNameService extends ServiceTemplate {
    @Autowired
    private ClassNameRepository repository;

    public boolean create(ClassNameModel classNameModel) throws ClassNameInvalidException {
        try{
            return super.create(classNameModel, this.repository);
        }catch (Exception error){
            throw new ClassNameInvalidException("Houve um problema para criar um Gap. Erro: "+error.getMessage(), 400);
        }
    }

    public List<ClassNameModel> read() {
        return (List<ClassNameModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public ClassNameModel findByUuid(String uuid) {
        return (ClassNameModel) super.findByUuid(uuid, this.repository);
    }
}
