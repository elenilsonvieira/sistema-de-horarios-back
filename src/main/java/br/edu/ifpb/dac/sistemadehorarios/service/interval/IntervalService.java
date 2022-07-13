package br.edu.ifpb.dac.sistemadehorarios.service.interval;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.DRO.IntervalDRO;
import br.edu.ifpb.dac.sistemadehorarios.controller.interval.GapController;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.IntervalModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.repository.interval.IntervalRepository;

@Service
public class IntervalService extends ServiceTemplate {
	
	@Autowired
	private IntervalRepository repository;

    @Autowired
    private GapService gapService;

    @Autowired
    private ShiftService shiftService;

    @Autowired WeekDayService weekDayService;
	
	public IntervalModel create(IntervalDRO intervalDRO) throws IntervalInvalidException {
        try{
            GapModel gap = this.gapService.findByUuid(intervalDRO.getGapUuid());
            ShiftModel shift = this.shiftService.findByUuid(intervalDRO.getShiftUuid());
            WeekDayModel weekDay = this.weekDayService.findByUuid(intervalDRO.getWeekDayUuid());

            if(gap != null || shift != null || weekDay != null){
                IntervalModel intervalModel = new IntervalModel();

                intervalModel.setGapModel(gap);
                intervalModel.setShiftModel(shift);
                intervalModel.setWeekDayModel(weekDay);
                boolean result = super.create(intervalModel, this.repository);
                return result ? intervalModel : null;
            }
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Algum valor inválido foi informado", 400);

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
