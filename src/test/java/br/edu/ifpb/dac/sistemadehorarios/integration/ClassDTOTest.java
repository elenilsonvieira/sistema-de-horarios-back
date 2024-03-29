package br.edu.ifpb.dac.sistemadehorarios.integration;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassDTOTest implements DTOTest {
    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
        List<TurmaModel> listClass = new ArrayList<TurmaModel>();
        TurmaModel classs = new TurmaModel();
        classs.setName("feras");
        listClass.add(classs);

        List<TurmaDTO> listDTO = new ArrayList<TurmaDTO>();
        listDTO.add(new TurmaDTO(classs));

        assertEquals(listDTO.get(0).getUuid() , TurmaDTO.convert(listClass).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , TurmaDTO.convert(listClass).get(0).getName());
        assertNotEquals(-1, TurmaDTO.convert(listClass).get(0).getUuid());
    }
}
