package sk.sfabian.export_module.model.target.track.kml_model;

import org.simpleframework.xml.Element;

public class KmlRouteOrientation {
    @Element
    private String heading;
    @Element
    private String tilt;
    @Element
    private String roll;

    public KmlRouteOrientation() {
    }

    public KmlRouteOrientation(String heading, String tilt, String roll) {
        this.heading = heading;
        this.tilt = tilt;
        this.roll = roll;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
