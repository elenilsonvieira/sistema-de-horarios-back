package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.ShiftException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService extends ServiceTemplate {
    @Autowired
    private ShiftRepository repository;

    public void createDefaultValues() throws ShiftException {
        for (ShiftEnum shiftEnum: ShiftEnum.values()) {
            ShiftModel shiftModel = this.findByShift(shiftEnum.getName());
            if(shiftModel == null){
                shiftModel = new ShiftModel();
                shiftModel.setShift(shiftEnum.getName());
                this.create(shiftModel);
            }
        }
    }

    public boolean create(ShiftModel shiftModel) throws ShiftException {
        try{
            return super.create(shiftModel, this.repository);
        }catch (Exception error){
            throw new ShiftException("Houve um problema para criar um Gap. Erro: "+error.getMessage());
        }
    }

    public List<ShiftModel> read() {
        return (List<ShiftModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }
    public ShiftModel findByUuid(String uuid) {
        return (ShiftModel) super.findByUuid(uuid, this.repository);
    }

    public ShiftModel findByShift(String shiftName){
        return repository.findByShift(shiftName);
    }
}
