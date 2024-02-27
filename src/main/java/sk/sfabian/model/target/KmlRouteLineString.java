package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "LineString")
public class KmlRouteLineString {
    @Element
    private int tessellate;

    @Element
    private String altitudeMode;

    @Element
    private String coordinates;

    public int getTessellate() {
        return tessellate;
    }

    public void setTessellate(int tessellate) {
        this.tessellate = tessellate;
    }

    public String getAltitudeMode() {
        return altitudeMode;
    }

    public void setAltitudeMode(String altitudeMode) {
        this.altitudeMode = altitudeMode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
