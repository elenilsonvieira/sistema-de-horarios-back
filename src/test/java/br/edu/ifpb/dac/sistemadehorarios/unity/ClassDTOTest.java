package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassDTOTest {
    @Test
    @DisplayName("Conversion Test")
    public void convertTest(){
        List<TurmaModel> listClass = new ArrayList<TurmaModel>();
        TurmaModel classs = new TurmaModel();
        classs.setName("feras");
        listClass.add(classs);

        List<TurmaDTO> listDTO = new ArrayList<TurmaDTO>();
        listDTO.add(new TurmaDTO(classs));

        assertEquals(listDTO.get(0).getUuid() , TurmaDTO.convert(listClass).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , TurmaDTO.convert(listClass).get(0).getName());
    }
}
