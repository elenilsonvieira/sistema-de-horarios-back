package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GapService extends ServiceTemplate {
    @Autowired
    private GapRepository repository;

    public boolean create(GapModel gapModel) throws GapInvalidException {
        try{
            return super.create(gapModel, this.repository);
        }catch (Exception error){
            throw new GapInvalidException("Houve um problema para criar um Gap. Erro: "+error.getMessage(), 400);
        }
    }

    public List<GapModel> read() {
        return (List<GapModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public GapModel findByUuid(String uuid) {
        return (GapModel) super.findByUuid(uuid, this.repository);
    }
}
