package sk.sfabian.import_module.kml_tour.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Camera")
public class KmlCamera {
    @Element(name = "horizFov")
    private String horizFov;

    @Element
    private String longitude;

    @Element
    private String latitude;

    @Element
    private String altitude;

    @Element
    private String heading;

    @Element
    private String tilt;

    @Element
    private String roll;

    @Element(name = "altitudeMode")
    private String altitudeMode;

    public String getHorizFov() {
        return horizFov;
    }

    public void setHorizFov(String horizFov) {
        this.horizFov = horizFov;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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

    public String getAltitudeMode() {
        return altitudeMode;
    }

    public void setAltitudeMode(String altitudeMode) {
        this.altitudeMode = altitudeMode;
    }
}
