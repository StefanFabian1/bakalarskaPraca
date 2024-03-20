package sk.sfabian.import_module.kml_tour.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gx:Wait")
public class KmlWait {
    @Element(name = "duration")
    private String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
