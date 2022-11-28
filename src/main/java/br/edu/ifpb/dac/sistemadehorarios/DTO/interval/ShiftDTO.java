package br.edu.ifpb.dac.sistemadehorarios.DTO.interval;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShiftDTO {
    private ShiftEnum shift;
    private String displayName;
    private String uuid;

    public ShiftDTO(ShiftModel shiftModel) {
        this.shift = shiftModel.getShiftEnum();
        this.displayName = shiftModel.getDisplayName();
        this.uuid = shiftModel.getUuid();
    }

    public static List<ShiftDTO> convert(List<ShiftModel> shiftModelList){
        return shiftModelList.stream().map(ShiftDTO::new).collect(Collectors.toList());
    }
}
