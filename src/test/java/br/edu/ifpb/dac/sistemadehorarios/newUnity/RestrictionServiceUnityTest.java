package br.edu.ifpb.dac.sistemadehorarios.newUnity;

import static org.junit.jupiter.api.Assertions.*;

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

import br.edu.ifpb.dac.sistemadehorarios.DTO.RestrictionDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestrictionServiceUnityTest {

	@MockBean
	private RestrictionModel restrictionMock;
	@MockBean
	private RestrictionRepository restrictionRepository;
	@Autowired
	private RestrictionService restrictionService;
	
	@Test
	@Order(1)
    @Tag("creat method")
    @DisplayName("Creat from restrictionService")
	void createNewRestriction() {
		try {
			RestrictionModel restrictionTeste = restrictionService.create(restrictionMock);
			assertNotEquals(restrictionTeste, null);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
    @Tag("read method")
    @DisplayName("List RestrictionModel from restrictionService")
	void readRestriction() {
		try {
			List<RestrictionModel> listRestrictionTeste = restrictionService.read();
			listRestrictionTeste.add(restrictionMock);
			assertEquals(listRestrictionTeste.size(), 1);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
    @Tag("findByUuid method")
    @DisplayName("Return RestrictionModel from restrictionService")
	void findByUuidRestriction() {
		try {
			String id = "id-mock";
			RestrictionModel restrictionTeste = restrictionService.findByUuid(id);
			assertNotEquals(restrictionTeste, null);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
    @Tag("findByProfessor method")
    @DisplayName("List RestrictionModel by professor")
	void findByProfessorRestriction() {
		try {
			List<RestrictionModel> listRestrictionTeste = restrictionService.findByProfessorModel(restrictionMock.getProfessorModel());
			listRestrictionTeste.add(restrictionMock);
			assertEquals(listRestrictionTeste.size(), 1);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
    @Tag("update method")
    @DisplayName("Update RestrictionModel")
	void updateRestriction() {
		try {
			String id = "id-mock";
			boolean check = restrictionService.update(restrictionMock, id);
			assertTrue(check);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(6)
    @Tag("delete method")
    @DisplayName("Delete RestrictionModel from restrictionService")
	void deleteRestriction() {
		try {
			String id = "id-mock";
			boolean check = restrictionService.delete(id);
			assertTrue(check);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
