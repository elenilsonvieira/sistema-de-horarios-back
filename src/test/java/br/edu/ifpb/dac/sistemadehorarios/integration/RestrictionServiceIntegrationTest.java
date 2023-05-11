package br.edu.ifpb.dac.sistemadehorarios.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileService;
import br.edu.ifpb.dac.sistemadehorarios.interfaces.ServiceTest;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.ifpb.dac.sistemadehorarios.DTO.RestrictionDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Restriction.RestrictionService;
import org.springframework.stereotype.Service;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestrictionServiceIntegrationTest implements ServiceTest {

	private static RestrictionModel restrictionModel;

	private static ProfessorDRO professorDRO;

	private static ProfileModel profileModel;

	private static ProfessorModel professorModel;

	@Autowired
	private RestrictionService restrictionService;

	@Autowired
	private ShiftService shiftService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private WeekDayService weekDayService;

	@BeforeAll
	public static void setUp() {
		restrictionModel = new RestrictionModel();
		restrictionModel.setUuid("id-test");

		professorDRO = new ProfessorDRO();
		professorDRO.setName("Fernandos");
		professorDRO.setProfileUuid("id-test2");

		profileModel = new ProfileModel();
		profileModel.setUuid("id-test2");
		profileModel.setField("Field");
		profileModel.setCreate_at(new Date());
		profileModel.setStandard(1);
	}

	@Test
	@Order(1)
	@DisplayName("Attributes are not null")
	@Override
	public void attributesAreNotNull() {
		assertNotNull(restrictionModel.getUuid());
		assertNotNull(professorDRO.getName());
		assertNotNull(professorDRO.getProfileUuid());
		assertNotNull(profileModel.getUuid());
		assertNotNull(profileModel.getField());
		assertNotNull(profileModel.getCreate_at());
		assertNotNull(profileModel.getStandard());
	}

	@Test
	@Order(2)
	@DisplayName("should be created a new restriction")
	@Override
	public void testCreateNewEntity() {
		try {
			profileService.create(profileModel);
			professorModel = professorService.create(professorDRO);

			restrictionModel.setShiftModel(this.shiftService.findByUuid("b9a9c5d3-a472-4f4b-a9c5-d3a4729f4bf3"));
			restrictionModel.setWeekDayModel(this.weekDayService.findByUuid("54460d47-25bf-4170-860d-4725bf217007"));
			restrictionModel.setProfessorModel(professorModel);

			RestrictionModel restrictionTest = restrictionService.create(restrictionModel);
			assertNotEquals(restrictionTest, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	@DisplayName("should be listed restrictions")
	@Override
	public void testReadEntities() {
		try {
			List<RestrictionModel> listRestrictionTest = restrictionService.read();
			assertNotEquals(listRestrictionTest.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(4)
	@DisplayName("should be found a restriction")
	@Override
	public void testFindOneEntityById() {
		try {
			String id = "id-test";
			RestrictionModel restrictionTest = restrictionService.findByUuid(id);
			assertNotEquals(restrictionTest, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(5)
	@DisplayName("should be updated a restriction")
	@Override
	public void testUpdateOneEntityById() {
		try {
			boolean check = restrictionService.update(restrictionModel, restrictionModel.getUuid());
			assertTrue(check);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(6)
	@DisplayName("should be deleted a restriction and professor")
	@Override
	public void testDeleteOneEntityById() {
		try {
			boolean hasRestriction = restrictionService.delete(restrictionModel.getUuid());
			boolean hasProfessor = professorService.delete(professorModel.getUuid());

			assertTrue(hasRestriction);
			assertTrue(hasProfessor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
