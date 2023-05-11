package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
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

    public void createDefaultValues() throws IntervalInvalidException {
        List<IntervalModel> intervalList = this.read();
        int minimumAmount = 96;

        if(intervalList.size() < minimumAmount ){
            List<GapModel> gapList = gapService.read();
            List<WeekDayModel> weekDayList = weekDayService.read();

            ShiftModel shiftMorning = shiftService.findByShiftEnum(ShiftEnum.MORNING);
            ShiftModel shiftAfternoon = shiftService.findByShiftEnum(ShiftEnum.AFTERNOON);
            ShiftModel shiftNight = shiftService.findByShiftEnum(ShiftEnum.NIGHT);
        
            for(WeekDayModel weekday: weekDayList){
                for(GapModel gap: gapList){
                    IntervalModel interval = new IntervalModel();
                    interval.setGapModel(gap);
                    interval.setWeekDayModel(weekday);
                    interval.setShiftModel(shiftMorning);
                    if(!intervalList.contains(interval)){
                        this.create(interval);
                    }
                }
            }

            for(WeekDayModel weekday: weekDayList){
                for(GapModel gap: gapList){
                    IntervalModel interval = new IntervalModel();
                    interval.setGapModel(gap);
                    interval.setWeekDayModel(weekday);
                    interval.setShiftModel(shiftAfternoon);
                    if(!intervalList.contains(interval)){
                        this.create(interval);
                    }
                }
            }

            for(WeekDayModel weekday: weekDayList){
                for(GapModel gap: gapList){
                    if(gap.getGapEnum().compareTo(GapEnum.FOURTH) < 1){
                        IntervalModel interval = new IntervalModel();
                        interval.setGapModel(gap);
                        interval.setWeekDayModel(weekday);
                        interval.setShiftModel(shiftNight);
                        if(!intervalList.contains(interval)){
                            this.create(interval);
                        }
                    }
                }
            }
        }
    }
	
    public boolean create(IntervalModel intervalModel) throws IntervalInvalidException {
        try{
            return super.create(intervalModel, this.repository);
        }catch (Exception error){
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Erro: " + error.getMessage(), 400);
        }
    }

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
