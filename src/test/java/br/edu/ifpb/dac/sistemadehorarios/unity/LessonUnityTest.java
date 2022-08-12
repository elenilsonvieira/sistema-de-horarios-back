package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassName.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters.Filter;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters.FilterByBlock;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters.FilterByClassName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


public class LessonUnityTest {

    private LessonService lessonService = mock(LessonService.class);

    private ClassroomModel classroomBuilder(String block, String name){
        ClassBlockModel classBlockModel = new ClassBlockModel();
        classBlockModel.setBlockName(block);

        ClassNameModel classNameModel = new ClassNameModel();
        classNameModel.setName(name);

        ClassroomModel classroomModel = new ClassroomModel();
        classroomModel.setClassNameModel(classNameModel);
        classroomModel.setClassBlockModel(classBlockModel);
        return classroomModel;
    }

    private List<LessonModel> getLessonsMock(){

        LessonModel lessonModel1 = new LessonModel();
        lessonModel1.setClassroomModel(this.classroomBuilder("Bloco A", "Sala 1"));

        LessonModel lessonModel2 = new LessonModel();
        lessonModel2.setClassroomModel(this.classroomBuilder("Bloco A", "Sala 2"));

        LessonModel lessonModel3 = new LessonModel();
        lessonModel3.setClassroomModel(this.classroomBuilder("Bloco A", "Sala 3"));

        LessonModel lessonModel4 = new LessonModel();
        lessonModel4.setClassroomModel(this.classroomBuilder("Bloco B", "Sala 4"));

        ArrayList<LessonModel> lessonModelArrayList = new ArrayList<LessonModel>();

        lessonModelArrayList.add(lessonModel1);
        lessonModelArrayList.add(lessonModel2);
        lessonModelArrayList.add(lessonModel3);
        lessonModelArrayList.add(lessonModel4);

        return lessonModelArrayList;
    }

    @Test
    public void testFilters(){

        when(lessonService.read()).thenReturn(this.getLessonsMock());

        List<LessonModel> list = this.lessonService.read();

        List<Filter> filters1 = new ArrayList<Filter>();
        filters1.add(new FilterByBlock("Bloco A"));
        filters1.add(new FilterByClassName("Sala 1"));
        list = Filter.getResult(filters1,list);
        assertEquals(1, list.size());

        List<Filter> filters2 = new ArrayList<Filter>();
        filters2.add(new FilterByBlock("Bloco A"));
        filters2.add(new FilterByClassName("null"));
        list = this.lessonService.read();
        list = Filter.getResult(filters2,list);
        assertEquals(3, list.size());

        List<Filter> filters3 = new ArrayList<Filter>();
        filters3.add(new FilterByBlock("null"));
        filters3.add(new FilterByClassName("null"));
        list = this.lessonService.read();
        list = Filter.getResult(filters3,list);
        assertEquals(4, list.size());
    }

    @Test
    public void filtersNull(){

        assertThrows(NullPointerException.class, () -> {
            when(lessonService.read()).thenReturn(this.getLessonsMock());

            List<LessonModel> list = this.lessonService.read();

            List<Filter> filters1 = new ArrayList<Filter>();
            filters1.add(null);
            filters1.add(null);
            list = Filter.getResult(filters1,list);
        });
    }


}
