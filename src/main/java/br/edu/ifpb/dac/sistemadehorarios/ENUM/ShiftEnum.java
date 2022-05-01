package br.edu.ifpb.dac.sistemadehorarios.ENUM;

import br.edu.ifpb.dac.sistemadehorarios.utils.shift.AfternoonShiftTemplate;
import br.edu.ifpb.dac.sistemadehorarios.utils.shift.MorningShiftTemplate;
import br.edu.ifpb.dac.sistemadehorarios.utils.shift.NightShiftTemplate;
import br.edu.ifpb.dac.sistemadehorarios.utils.shift.ShiftTemplate;

public enum ShiftEnum {
    MORNING,
    AFTERNOON,
    NIGHT;

    public ShiftTemplate factory(ShiftEnum shiftEnum){
        switch (shiftEnum){
            case AFTERNOON:
                return new AfternoonShiftTemplate();
            case MORNING:
                return new MorningShiftTemplate();
            default:
                return new NightShiftTemplate();
        }
    }
}
