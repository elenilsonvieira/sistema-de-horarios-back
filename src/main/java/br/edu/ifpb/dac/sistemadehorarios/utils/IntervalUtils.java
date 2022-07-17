package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval.IntervalRepository;

public class IntervalUtils {
    public static boolean isValidInterval(IntervalModel interval, IntervalRepository repository) throws IntervalInvalidException {

        IntervalModel result = repository.findByDayAndInterval(
                interval.getWeekDayModel().getUuid(),
                interval.getShiftModel().getUuid(),
                interval.getGapModel().getUuid());

        return result == null;

    }
}
