package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.exception.CourseInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.IntervalRepository;

@Service
public class IntervalService extends ServiceTemplate {
	
	@Autowired
	private IntervalRepository repository;
	
	public boolean create(IntervalModel gap) throws IntervalInvalidException {
        try{
            return super.create(gap, this.repository);
        }catch (Exception error){
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Erro: "+error.getMessage(), 400);
        }
    }
	
	public List<IntervalModel> read() {
        return (List<IntervalModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public IntervalModel findByUuid(String uuid) {
        return (IntervalModel) super.findByUuid(uuid, this.repository);
    }



}
