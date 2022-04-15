package br.edu.ifpb.dac.sistemadehorarios.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "professor")
public class ProfessorModel implements Serializable {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
    private String name;
    private String area;
    private Date create_at;

    public ProfessorModel(String name, String area) {
        this.uuid =  String.valueOf(UUID.randomUUID());
        this.create_at = new Date();
        this.name = name;
        this.area = area;
    }

    public ProfessorModel(String name, String area, String uuid) {
        this.uuid =  uuid;
        this.create_at = new Date();
        this.name = name;
        this.area = area;
    }

}
