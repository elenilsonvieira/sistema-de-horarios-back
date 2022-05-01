package br.edu.ifpb.dac.sistemadehorarios.utils.shift;

public class MorningShiftTemplate implements ShiftTemplate {
    @Override
    public String one() {
        return "07:00";
    }

    @Override
    public String two() {
        return "07:50";
    }

    @Override
    public String three() {
        return "08:40";
    }

    @Override
    public String four() {
        return "09:30";
    }

    @Override
    public String five() {
        return "10:20";
    }

    @Override
    public String six() {
        return "11:10+";
    }
}
