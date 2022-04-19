package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;

import br.edu.ifpb.dac.sistemadehorarios.repository.ClassRepository;
import br.edu.ifpb.dac.sistemadehorarios.repository.CorricularComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CorricularComponentService {
    @Autowired
    private CorricularComponentRepository repository;

    @Autowired
    private ClassRepository classRepository;

    public boolean create(CorricularComponentModel corricularComponentModel, String uuid) {
        try {
            ClassModel classModel =  this.classRepository.findByUuid(uuid);
            System.out.println(classModel);
            if(classModel == null && corricularComponentModel.getClassModel() == null){
                return false;
            }
            corricularComponentModel.setClassModel(classModel);
            this.repository.save(corricularComponentModel);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public List<CorricularComponentModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
    }

    public boolean update(CorricularComponentModel corricularComponentModel, String uuid) {
        try {
            CorricularComponentModel result = this.repository.findByUuid(uuid);

            String name = corricularComponentModel.getName()==null? result.getName() : corricularComponentModel.getName();
            byte workload = corricularComponentModel.getWorkload()==0? result.getWorkload() : corricularComponentModel.getWorkload();


            result.setName(name);
            result.setWorkload(workload);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public boolean delete(String uuid) {
        try {
            this.repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public CorricularComponentModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }
}
