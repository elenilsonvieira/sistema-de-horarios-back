package br.edu.ifpb.dac.sistemadehorarios.DTO.interval;

import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.ShiftModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShiftDTO {
    private String shift;
    private String uuid;

    public ShiftDTO(ShiftModel shiftModel) {
        this.shift = shiftModel.getShift();
        this.uuid = shiftModel.getUuid();
    }

    public static List<ShiftDTO> convert(List<ShiftModel> shiftModelList){
        return shiftModelList.stream().map(ShiftDTO::new).collect(Collectors.toList());
    }
}
