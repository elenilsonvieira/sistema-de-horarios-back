package br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent;

import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Turma.TurmaService;
import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;

import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CurricularComponentService extends ServiceTemplate {
    @Autowired
    private CurricularComponentRepository repository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SuapService suapService;
    @Value("${Campus}")
    private String suapToken;

    public CurricularComponentModel create(CurricularComponentDRO DRO) throws CurricularComponentInvalidException {
        try {
            CourseModel courseModel =  courseService.findByUuid(DRO.getCourseUuid());
            if(courseModel==null){
                throw new CurricularComponentInvalidException("O Curso não existe", 400);
            }
            CurricularComponentModel corricularComponent = new CurricularComponentModel();
            corricularComponent.setName(DRO.getName());
            corricularComponent.setWorkload(DRO.getWorkload());
            corricularComponent.setCourseUuid(courseModel);
            super.create(corricularComponent, this.repository);
            return corricularComponent;
        }catch (Exception error){
            throw new CurricularComponentInvalidException("Houve um problema para criar um Curricular Component. Erro: "+error.getMessage(), 400);
        }
    }

    public List<CurricularComponentModel> read() {
       return (List<CurricularComponentModel>) super.read(this.repository);
    }

    public CurricularComponentModel update(CurricularComponentModel curricularComponentModel, String uuid) {
        try {
            CurricularComponentModel result = this.repository.findByUuid(uuid);

            String name = curricularComponentModel.getName()==null? result.getName() : curricularComponentModel.getName();
            int workload = curricularComponentModel.getWorkload()==0? result.getWorkload() : curricularComponentModel.getWorkload();
            result.setName(name);
            result.setWorkload(workload);

            return this.repository.save(result);
        }catch (Exception error){
            return null;
        }
    }

    public boolean delete(String uuid) {
       return super.delete(uuid, this.repository);
    }

    public CurricularComponentModel findByUuid(String uuid) {
       return (CurricularComponentModel) super.findByUuid(uuid, this.repository);
    }

    public void createDefaultValues() throws CourseInvalidException, JsonProcessingException {
        if(repository.findAll().isEmpty()) {
            List<CurricularComponentModel> curricularComponentModels = new ArrayList<>();
            this.suapService.setSuapToken(suapToken);
            ObjectMapper mapper = new ObjectMapper();
            int count = 1;
            boolean isFinished = false;
            while(!isFinished) {
                CourseModel courseModel = new CourseModel();
                var coursesInString = this.suapService.findCurricular(count);
                JsonNode jsonNode = mapper.readTree(coursesInString);
                JsonNode results = jsonNode.get("results");
                if(results != null) {
                    for (JsonNode node : results) {
                        if(node.get("descricao").asText().contains("Monteiro")) {
                            courseModel.setName(node.get("descricao").asText());
                            this.courseService.create(courseModel);
                            for (JsonNode nodeJunior : node.get("componentes")) {
                                CurricularComponentModel curricularComponentModel = new CurricularComponentModel();
                                curricularComponentModel.setName(nodeJunior.get("descricao").asText());
                                curricularComponentModel.setWorkload(nodeJunior.get("ch_presencial").asInt());
                                curricularComponentModel.setCourseUuid(courseModel);
                                curricularComponentModels.add(curricularComponentModel);
                                System.out.println(curricularComponentModels);
                            }
                        }
                    }
                }

                jsonNode = mapper.readTree(coursesInString);
                if(jsonNode.get("next") == null) {
                    isFinished = true;
                }
                count++;
            }

            System.out.println("Adicionando ao banco");
            this.repository.saveAll(curricularComponentModels);
        }

    }

}
