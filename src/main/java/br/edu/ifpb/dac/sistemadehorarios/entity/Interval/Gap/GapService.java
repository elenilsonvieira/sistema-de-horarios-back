package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GapService extends ServiceTemplate {
    @Autowired
    private GapRepository repository;

    public void createDefaultValues() throws GapException {
        for (GapEnum gapEnum: GapEnum.values()) {
            GapModel gap = this.findByDisplayName(gapEnum.getName());
            if(gap == null){
                gap = new GapModel();
                gap.setGapEnum(gapEnum);
                gap.setDisplayName(gapEnum.getName());
                this.create(gap);
            }
        }
    }

    public boolean create(GapModel gapModel) throws GapException {
        try{
            return super.create(gapModel, this.repository);
        }catch (Exception error){
            throw new GapException("Houve um problema para criar um Gap. Erro: "+error.getMessage());
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

    public GapModel findByGapEnum(GapEnum gap){
        return repository.findByGapEnum(gap);
    }

    public GapModel findByDisplayName(String displayName){
        return this.repository.findByDisplayName(displayName);
    }
}
