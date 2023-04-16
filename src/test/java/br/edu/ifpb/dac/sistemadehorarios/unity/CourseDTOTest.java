package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CourseDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.DTOTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CourseDTOTest implements DTOTest {

    @Test
    @Override
    @DisplayName("Test Conversion Model to DTO")
    public void convert(){
        List<CourseModel> listCourses = new ArrayList<CourseModel>();
        CourseModel courseModel = new CourseModel();
        courseModel.setName("TT");
        listCourses.add(courseModel);

        List<CourseDTO> listDTO = new ArrayList<CourseDTO>();
        listDTO.add(new CourseDTO(courseModel));

        assertEquals(listDTO.get(0).getUuid() , CourseDTO.convert(listCourses).get(0).getUuid());
        assertEquals(listDTO.get(0).getName() , CourseDTO.convert(listCourses).get(0).getName());
        assertNotEquals(-1, CourseDTO.convert(listCourses).get(0).getUuid());

    }
}
