package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassBlockDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClassBlockDTOTest implements DTOTest {

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert() {
        List<ClassBlockModel> listBlock = new ArrayList<ClassBlockModel>();
        ClassBlockModel blockModel = new ClassBlockModel();
        blockModel.setBlockName("D");
        listBlock.add(blockModel);

        List<ClassBlockDTO> listDTO = new ArrayList<ClassBlockDTO>();
        listDTO.add(new ClassBlockDTO(blockModel));

        assertEquals(listDTO.get(0).getUuid() , ClassBlockDTO.convert(listBlock).get(0).getUuid());
        assertEquals(listDTO.get(0).getBlock() , ClassBlockDTO.convert(listBlock).get(0).getBlock());
        assertNotEquals(-1, ClassBlockDTO.convert(listBlock).get(0).getUuid());
    }
}
