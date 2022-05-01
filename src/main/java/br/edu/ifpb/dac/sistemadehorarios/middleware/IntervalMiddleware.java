package br.edu.ifpb.dac.sistemadehorarios.middleware;

import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.IntervalRepository;
import br.edu.ifpb.dac.sistemadehorarios.utils.IntervalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IntervalMiddleware {

    @Autowired
    private IntervalRepository repository;


    public void isValidInterval(IntervalModel interval) throws IntervalInvalidException {
        boolean result = IntervalUtils.isValidInterval(interval, this.repository);
        if(!result){
            throw new IntervalInvalidException("Interval already exists");
        }
    }
}
