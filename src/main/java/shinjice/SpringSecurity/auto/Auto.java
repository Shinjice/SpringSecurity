package shinjice.SpringSecurity.auto;

public class Auto {
    private final Integer autoId;
    private final String autoModel;

    public Auto(Integer autoId, String autoModel) {
        this.autoId = autoId;
        this.autoModel = autoModel;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public String getAutoModel() {
        return autoModel;
    }
}
