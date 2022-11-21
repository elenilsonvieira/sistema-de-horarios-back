package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftService;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntervalService extends ServiceTemplate {
	
	@Autowired
	private IntervalRepository repository;

    @Autowired
    private GapService gapService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private WeekDayService weekDayService;

	
	public IntervalModel create(IntervalDRO intervalDRO) throws IntervalInvalidException {
        try{
            GapModel gap = this.gapService.findByUuid(intervalDRO.getGapUuid());
            ShiftModel shift = this.shiftService.findByUuid(intervalDRO.getShiftUuid());
            WeekDayModel weekDay = this.weekDayService.findByUuid(intervalDRO.getWeekDayUuid());

            if(gap != null || shift != null || weekDay != null) {
                IntervalModel intervalModel = new IntervalModel();

                intervalModel.setGapModel(gap);
                intervalModel.setShiftModel(shift);
                intervalModel.setWeekDayModel(weekDay);

                boolean resultInterval = super.create(intervalModel, this.repository);
                return resultInterval == true ? intervalModel : null;
            }
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Algum valor inválido foi informado", 400);

        } catch (Exception error){
            System.out.println(error);
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Erro: "+error.getMessage(), 400);
        }
    }
	
	public List<IntervalModel> read() {
        return (List<IntervalModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        boolean resultInterval = super.delete(uuid, this.repository);
        return resultInterval;
    }

    public IntervalModel findByUuid(String uuid) {
        return (IntervalModel) super.findByUuid(uuid, this.repository);
    }

}
