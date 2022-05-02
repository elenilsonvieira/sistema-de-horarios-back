package br.edu.ifpb.dac.sistemadehorarios.DRO;

public class CurricularComponentDRO {
    private String name;
    private byte workload;
    private String courseUuid;
    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getWorkload() {
        return workload;
    }

    public void setWorkload(byte workload) {
        this.workload = workload;
    }

    public String getCourseUuid() {
        return courseUuid;
    }

    public void setCourseUuid(String courseUuid) {
        this.courseUuid = courseUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
