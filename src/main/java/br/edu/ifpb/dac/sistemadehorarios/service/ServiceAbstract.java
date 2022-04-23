package br.edu.ifpb.dac.sistemadehorarios.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public abstract class ServiceAbstract {

    protected <T> boolean create(T object, JpaRepository repository) {
        try {
            if(object == null){
                return false;
            }
            repository.save(object);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    protected List<?> read( JpaRepository repository) {
        try {
            return repository.findAll();
        }catch (Exception error){
            return null;
        }
    }

    protected boolean delete(String uuid, JpaRepository repository) {
        try {
            repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    protected <T> Object findByUuid(String uuid, JpaRepository repository) {
        try {
            return repository.findById(uuid).get();
        }catch (Exception error){
            return null;
        }
    }
}
