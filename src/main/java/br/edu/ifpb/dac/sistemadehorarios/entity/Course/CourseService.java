package br.edu.ifpb.dac.sistemadehorarios.entity.Course;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapException;
import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends ServiceTemplate {
	
	@Autowired
	private CourseRepository repository;

    @Autowired
    private SuapService suapService;

    @Value("${Campus}")
    private String suapToken;
	
	public boolean create(CourseModel courseModel) throws CourseInvalidException {

        try{
            return super.create(courseModel, this.repository);
        }catch (Exception error){
            throw new CourseInvalidException("Houve um problema para criar um Course. Erro: "+error.getMessage(), 400);
        }
    }

    public List<CourseModel> read() {
       return (List<CourseModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public CourseModel findByUuid(String uuid) {
        return (CourseModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(CourseModel courseModel, String uuid) {
        try {
        	CourseModel result = this.repository.findByUuid(uuid);

            String name = courseModel.getName()==null? result.getName() : courseModel.getName();
            result.setName(name);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public void createDefaultValues() throws CourseInvalidException, JsonProcessingException {
        if(repository.findAll().isEmpty()) {
            this.suapService.setSuapToken(suapToken);
            var coursesInString = this.suapService.findAllCourses();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(coursesInString).get("results");
            List<CourseModel> courseModels = new ArrayList<>();
            for (JsonNode node : jsonNode) {
                if(node.get("diretoria").asText().contains("MONTEIRO")) {
                    CourseModel courseModel = new CourseModel();
                    courseModel.setUuid(node.get("uuid").asText());
                    courseModel.setName(node.get("descricao").asText());
                    courseModels.add(courseModel);
                }

            }

            this.repository.saveAll(courseModels);
        }
    }

}
