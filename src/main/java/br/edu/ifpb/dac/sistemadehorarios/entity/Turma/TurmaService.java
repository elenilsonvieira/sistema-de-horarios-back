package br.edu.ifpb.dac.sistemadehorarios.entity.Turma;

import br.edu.ifpb.dac.sistemadehorarios.exception.TurmaInvalidException;

import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService extends ServiceTemplate {
	
    @Autowired
    private TurmaRepository repository;

    public TurmaModel create(TurmaModel turma) throws TurmaInvalidException {
        try {
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
