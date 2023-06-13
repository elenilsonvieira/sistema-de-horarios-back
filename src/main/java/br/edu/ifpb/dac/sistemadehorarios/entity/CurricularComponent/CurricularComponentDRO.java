package br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurricularComponentDRO {
    private String name;
    private int workload;
    private String courseUuid;
}
