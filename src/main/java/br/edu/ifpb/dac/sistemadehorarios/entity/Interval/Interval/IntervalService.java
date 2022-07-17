package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import java.util.Date;
import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapService;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Gap.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonService;
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

    @Autowired
    private LessonService lessonService;
	
	public IntervalModel create(IntervalDRO intervalDRO) throws IntervalInvalidException {
        try{
            LessonModel lesson = this.lessonService.findByUuid(intervalDRO.getLessonUuid());
            if(lesson.getIntervalModel() != null){
                throw new IntervalInvalidException("Houve um problema para criar um Interval. Já existe um intervalo nessa aula", 400);

            }
            GapModel gap = this.gapService.findByUuid(intervalDRO.getGapUuid());
            ShiftModel shift = this.shiftService.findByUuid(intervalDRO.getShiftUuid());
            WeekDayModel weekDay = this.weekDayService.findByUuid(intervalDRO.getWeekDayUuid());

            if(gap != null || shift != null || weekDay != null || lesson != null){
                IntervalModel intervalModel = new IntervalModel();

                intervalModel.setGapModel(gap);
                intervalModel.setShiftModel(shift);
                intervalModel.setWeekDayModel(weekDay);

                lesson.setIntervalModel(intervalModel);
                lesson.setUpdate_at(new Date());
                boolean resultInterval = super.create(intervalModel, this.repository);
                boolean resultLesson = this.lessonService.update(lesson);
                return (resultInterval && resultLesson) ? intervalModel : null;
            }
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Algum valor inválido foi informado", 400);

        }catch (Exception error){
            System.out.println(error);
            throw new IntervalInvalidException("Houve um problema para criar um Interval. Erro: "+error.getMessage(), 400);
        }
    }
	
	public List<IntervalModel> read() {
        return (List<IntervalModel>) super.read(this.repository);
    }
    public boolean delete(String uuid, String lessonUuid) {
        LessonModel lesson = this.lessonService.findByUuid(lessonUuid);
        lesson.setIntervalModel(null);
        lesson.setUpdate_at(new Date());
        boolean resultLesson = this.lessonService.update(lesson);
        boolean resultInterval = super.delete(uuid, this.repository);
        return resultInterval && resultLesson;
    }

    public IntervalModel findByUuid(String uuid) {
        return (IntervalModel) super.findByUuid(uuid, this.repository);
    }



}
