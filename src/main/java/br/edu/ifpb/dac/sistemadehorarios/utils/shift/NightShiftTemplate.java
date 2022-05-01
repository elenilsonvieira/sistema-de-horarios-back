package br.edu.ifpb.dac.sistemadehorarios.utils.shift;

public class NightShiftTemplate implements ShiftTemplate {
    @Override
    public String one() {
        return "18:30";
    }

    @Override
    public String two() {
        return "19:20";
    }

    @Override
    public String three() {
        return "20:10";
    }

    @Override
    public String four() {
        return "21:00";
    }

    @Override
    public String five() {
        return "21:50";
    }

    @Override
    public String six() {
        return "22:40+";
    }
}
