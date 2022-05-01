package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.IntervalRepository;

public class IntervalUtils {
    public static boolean isValidInterval(IntervalModel interval, IntervalRepository repository) throws IntervalInvalidException {

        IntervalModel result = repository.findByDayAndInterval(
                interval.getDayOfWeek().name(),
                interval.getShift().name(),
                interval.getInterval());

        return result == null;

    }
}
