package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CalendarDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassBlockDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassBlockDTOTest {

    @Test
    @DisplayName("Test Conversion Model to DTO")
    public void convertTest() {
        List<ClassBlockModel> listBlock = new ArrayList<ClassBlockModel>();
        ClassBlockModel blockModel = new ClassBlockModel();
        blockModel.setBlockName("D");
        listBlock.add(blockModel);

        List<ClassBlockDTO> listDTO = new ArrayList<ClassBlockDTO>();
        listDTO.add(new ClassBlockDTO(blockModel));

        assertEquals(listDTO.get(0).getUuid() , ClassBlockDTO.convert(listBlock).get(0).getUuid());
        assertEquals(listDTO.get(0).getBlock() , ClassBlockDTO.convert(listBlock).get(0).getBlock());
    }
}
