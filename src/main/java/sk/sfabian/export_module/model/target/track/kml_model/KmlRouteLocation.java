package sk.sfabian.export_module.model.target.track.kml_model;

import org.simpleframework.xml.Element;

public class KmlRouteLocation {
    @Element
    private String longitude;
    @Element
    private String latitude;
    @Element
    private String altitude;

    public KmlRouteLocation() {
    }

    public KmlRouteLocation(String longitude, String latitude, String altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
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
}
