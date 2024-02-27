package sk.sfabian.model.source;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gx:Wait")
public class KmlWait implements KmlElement {
    @Element(name = "duration")
    private String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
