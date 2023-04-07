package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.DTO.CourseDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.UserDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDTOTest {

    @Test
    public void convertTest(){
        List<CourseModel> listCourses = new ArrayList<CourseModel>();
        CourseModel courseModel = new CourseModel();
        courseModel.setName("TT");
        listCourses.add(courseModel);

        List<CourseDTO> listDTO = new ArrayList<CourseDTO>();
        listDTO.add(new CourseDTO(courseModel));

        assertEquals(listDTO.get(0).getName() , CourseDTO.convert(listCourses).get(0).getName());

    }
}
