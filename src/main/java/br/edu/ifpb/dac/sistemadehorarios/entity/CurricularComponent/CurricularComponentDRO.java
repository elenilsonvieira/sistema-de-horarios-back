package br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurricularComponentDRO {
    private String name;
    private byte workload;
    private String courseUuid;
}
