package br.edu.ifpb.dac.sistemadehorarios.utils.shift;

public class AfternoonShiftTemplate implements ShiftTemplate {
    @Override
    public String one() {
        return "13:00";
    }

    @Override
    public String two() {
        return "13:50";
    }

    @Override
    public String three() {
        return "14:40";
    }

    @Override
    public String four() {
        return "15:30";
    }

    @Override
    public String five() {
        return "16:20";
    }

    @Override
    public String six() {
        return "17:10+";
    }
}
