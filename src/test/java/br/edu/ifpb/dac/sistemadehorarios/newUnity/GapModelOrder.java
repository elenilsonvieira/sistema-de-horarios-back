package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GapModelOrder {
    
    
    @Test
	@Order(1)
    @Tag("order")
    @DisplayName("Logic of order")
    void testeSet() {

        SortedSet<GapModel> gapModels = new TreeSet<GapModel>();

        GapModel gap1 = new GapModel();
        gap1.setGapEnum(GapEnum.valueOf("THIRD"));
        GapModel gap2 = new GapModel();
        gap2.setGapEnum(GapEnum.valueOf("FRIST"));
        GapModel gap3 = new GapModel();
        gap3.setGapEnum(GapEnum.valueOf("SECOND"));

        gapModels.add(gap1);
        gapModels.add(gap2);
        gapModels.add(gap3);

        for(GapModel gap : gapModels){
            System.out.println(gap.getDisplayName());
        }
        assertNotEquals(gapModels, null);
    }


    @Test
	@Order(2)
    @Tag("order")
    @DisplayName("Logic of order")
    void testeList() {

        List<GapModel> gapModels = new ArrayList<GapModel>();

        GapModel gap1 = new GapModel();
        gap1.setGapEnum(GapEnum.valueOf("THIRD"));
        GapModel gap2 = new GapModel();
        gap2.setGapEnum(GapEnum.valueOf("FRIST"));
        GapModel gap3 = new GapModel();
        gap3.setGapEnum(GapEnum.valueOf("SECOND"));

        gapModels.add(gap1);
        gapModels.add(gap2);
        gapModels.add(gap3);

        Collections.sort(gapModels);

        for(GapModel gap : gapModels){
            System.out.println(gap.getDisplayName());
        }
        
        assertNotEquals(gapModels, null);
    }

    @Test
	@Order(2)
    @Tag("order")
    @DisplayName("Logic of enum")
    void testeListEnum() {
        for (GapEnum gapEnum: GapEnum.values()) {
            System.out.println(gapEnum);
            System.out.println(gapEnum.getName()); 
        }
    }

    @Test
	@Order(3)
    @Tag("order")
    @DisplayName("Gap get index")
    void testeListGetIndex() {
        List<GapModel> gapModels = new ArrayList<GapModel>();

        GapModel gap1 = new GapModel();
        gap1.setGapEnum(GapEnum.valueOf("THIRD"));
        GapModel gap2 = new GapModel();
        gap2.setGapEnum(GapEnum.valueOf("FRIST"));
        GapModel gap3 = new GapModel();
        gap3.setGapEnum(GapEnum.valueOf("SECOND"));

        gapModels.add(gap1);
        gapModels.add(gap2);
        gapModels.add(gap3);

        Collections.sort(gapModels);
        assertEquals(gapModels.get(gapModels.size() -1).getGapEnum(), GapEnum.valueOf("THIRD"));
    }


}
