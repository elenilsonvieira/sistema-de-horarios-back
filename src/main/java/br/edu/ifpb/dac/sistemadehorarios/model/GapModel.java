package br.edu.ifpb.dac.sistemadehorarios.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "gap")
public class GapModel {
	
	@Id
    @Column(name = "uuid", nullable = false)
    private String uuid;
	private String start;
	private String dayOfWeek;
	
	public GapModel() {
	}

	public GapModel(String start, String dayOfWeek) {
		this.uuid =  String.valueOf(UUID.randomUUID());
		this.start = start;
		this.dayOfWeek = dayOfWeek;
	}

	public String getUuid() {
		return uuid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
}
