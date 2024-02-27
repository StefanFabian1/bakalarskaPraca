package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "LookAt")
public class KmlRouteLookAt {
    @Element
    private double longitude;

    @Element
    private double latitude;

    @Element
    private double altitude;

    @Element
    private double heading;

    @Element
    private double tilt;

    @Element
    private double range;

    @Element(name = "gx:altitudeMode")
    private String altitudeMode;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getTilt() {
        return tilt;
    }

    public void setTilt(double tilt) {
        this.tilt = tilt;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public String getAltitudeMode() {
        return altitudeMode;
    }

    public void setAltitudeMode(String altitudeMode) {
        this.altitudeMode = altitudeMode;
    }
}
