package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassBlockDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassroomDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClassroomDTOTest {

    @Test
    @DisplayName("Test Conversion Model to DTO")
    public void convertTest(){
        List<ClassroomModel> listClass = new ArrayList<ClassroomModel>();

        ClassroomModel classs = new ClassroomModel();
        ClassBlockModel block = new ClassBlockModel();
        block.setBlockName("D");
        classs.setName("LAB 6");
        classs.setCapacity(40);
        classs.setClassBlockModel(block);
        listClass.add(classs);

        List<ClassroomDTO> listDTO = new ArrayList<ClassroomDTO>();
        listDTO.add(new ClassroomDTO(classs));

        assertEquals(listDTO.get(0).getUuid() , ClassroomDTO.convert(listClass).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , ClassroomDTO.convert(listClass).get(0).getName());
        assertNotEquals(-1, ClassroomDTO.convert(listClass).get(0).getUuid());
    }
}
