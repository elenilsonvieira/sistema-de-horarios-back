package br.edu.ifpb.dac.sistemadehorarios.DTO.interval;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GapDTO {
    private String gap;
    private String uuid;

    public GapDTO(GapModel gapModel) {
        this.gap = gapModel.getGap();
        this.uuid = gapModel.getUuid();
    }

    public static List<GapDTO> convert(List<GapModel> gapModelList){
        return gapModelList.stream().map(GapDTO::new).collect(Collectors.toList());
    }
}
