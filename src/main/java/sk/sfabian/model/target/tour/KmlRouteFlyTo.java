package sk.sfabian.model.target.tour;

import org.simpleframework.xml.Element;

public class KmlRouteFlyTo {
    @Element(name = "gx:duration", required = false)
    private String duration;
    @Element(name = "gx:flyToMode")
    private String flyToMede = "smooth";
    @Element(name = "LookAt")
    private KmlRouteLookAt lookAt = new KmlRouteLookAt(true);
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFlyToMede() {
        return flyToMede;
    }

    public void setFlyToMede(String flyToMede) {
        this.flyToMede = flyToMede;
    }

    public KmlRouteLookAt getLookAt() {
        return lookAt;
    }

    public void setLookAt(KmlRouteLookAt lookAt) {
        this.lookAt = lookAt;
    }

}
