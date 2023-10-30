package br.edu.ifpb.dac.sistemadehorarios.entity.Professor;


import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.CurricularComponent.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileRepository;
import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileService;
import br.edu.ifpb.dac.sistemadehorarios.exception.ProfessorInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.service.SuapService;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfessorService extends ServiceTemplate {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private SuapService suapService;

    @Autowired
    private ProfileService  profileService;


    @Autowired
    private ProfileRepository profileRepository;

    @Value("${Campus}")
    private String suapToken;

    public ProfessorModel create(ProfessorDRO professor) throws ProfessorInvalidException {
        try{
            ProfileModel profile = profileRepository.findByUuid(professor.getProfileUuid());

            if(profile != null){
                ProfessorModel professorModel = new ProfessorModel();

                professorModel.setName(professor.getName());
                professorModel.setProfileModel(profile);

                boolean result = super.create(professorModel, this.repository);
                return result ? professorModel : null;
            }
            throw new ProfessorInvalidException("Houve um problema para criar uma Classroom. Algum valor inválido foi informado", 400);

        }catch (Exception error) {
            throw new ProfessorInvalidException("Houve um problema para criar um Professor. Error: "+error.getMessage(), 400);
        }
    }

    public List<ProfessorModel> read() {
        return (List<ProfessorModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public ProfessorModel findByUuid(String uuid) {
        return (ProfessorModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(ProfessorDTO professorModel, String uuid) {
        try {
            ProfessorModel result = this.repository.findByUuid(uuid);
            String name = professorModel.getName().equals("")? result.getName() : professorModel.getName();
            ProfileModel resultProfileModel = profileService.findByUuid(professorModel.getProfile().getUuid());
            ProfileModel newProfile = resultProfileModel != null ? resultProfileModel : result.getProfileModel();
            result.setName(name);
            result.setProfileModel(newProfile);
            result.setUpdate_at(new Date());
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public void createDefaultValues() throws JsonProcessingException {
        if(repository.findAll().isEmpty()) {
            List<ProfessorModel> professorModels = new ArrayList<>();
            this.suapService.setSuapToken(suapToken);
            ObjectMapper mapper = new ObjectMapper();
            int count = 100;
            boolean isFinished = false;
            while(!isFinished) {
                var professorInString = this.suapService.findProfessors(count);
                JsonNode jsonNode = mapper.readTree(professorInString);
                JsonNode results = jsonNode.get("results");
                if(results != null) {
                    for (JsonNode node : results) {
                        if (node.has("lotacao_suap") && !node.get("lotacao_suap").isNull()) {
                            JsonNode lotacaoSuapNode = node.get("lotacao_suap");
                            String nomeLotacaoSuap = lotacaoSuapNode.get("nome").asText();

                            if(verificaString(nomeLotacaoSuap) && node.get("cargo_emprego").asText().contains("PROFESSOR")) {
                                ProfessorModel professorModel = new ProfessorModel();
                                ProfileModel profileModel = new ProfileModel();
                                JsonNode situacaoSuapNode = node.get("situacao");
                                String situacaoSuapString = situacaoSuapNode.get("codigo").asText();
                                profileModel.setStandard(Integer.parseInt(situacaoSuapString));
                                profileModel.setField(node.get("cargo_emprego").asText());
                                professorModel.setName(node.get("nome").asText());
                                this.profileRepository.save(profileModel);
                                professorModel.setProfileModel(profileModel);
                                professorModels.add(professorModel);
                            }
                        }
                    }
                }

                if(count == 1300) count += 200;
                else if(count == 3100) isFinished = true;
                else count += 100;

                System.out.println(count);
            }

            System.out.println("Adicionando ao banco...");
            this.repository.saveAll(professorModels);
        }
    }

    public static boolean verificaString(String texto) {
        // Criar a expressão regular com o padrão case-insensitive
        String regex = "(?i)\\bCOORDENAÇÃO\\b.*\\bCAMPUS MONTEIRO\\b";

        // Compilar a expressão regular em um padrão
        Pattern pattern = Pattern.compile(regex);

        // Criar o objeto Matcher para encontrar correspondências na string
        Matcher matcher = pattern.matcher(texto);

        // Verificar se há correspondências
        return matcher.find();
    }
}