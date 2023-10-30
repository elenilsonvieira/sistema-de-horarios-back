package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.Profile.ProfileModel;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfileDTO {
    
    private String field;
    private Integer standard;
	private String uuid;

    public ProfileDTO(ProfileModel profile) {
        this.field = profile.getField();
        this.standard = profile.getStandard();
		this.uuid = profile.getUuid();
    }
    public ProfileDTO() {
    }

    public static List<ProfileDTO> convert(List<ProfileModel> profiles){
        return profiles.stream().map(ProfileDTO::new).collect(Collectors.toList());
    }

}
