package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import sk.sfabian.model.target.tour.KmlRouteLookAt;
import sk.sfabian.model.target.track.KmlRouteTrack;

@Root(name = "Placemark")
public class KmlRoutePlacemark {
    @Element
    private String name;
    @Element
    private String styleUrl;
    @Element
    private String description;
    @Element(name = "gx:Track")
    private KmlRouteTrack track;
    @Element(name = "LookAt", type = KmlRouteLookAt.class, required = false)
    private KmlRouteLookAt lookAt;
    @Element(name = "LineString", type = KmlRouteLineString.class, required = false)
    private KmlRouteLineString lineString;
    public KmlRoutePlacemark() {}
    public KmlRoutePlacemark(String documentName, KmlRouteTrack track) {
        this.name = documentName + " Placemark";
        this.description = "";
        this.styleUrl = "#multiTrack";
        this.track = track;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KmlRouteLookAt getLookAt() {
        return lookAt;
    }

    public void setLookAt(KmlRouteLookAt lookAt) {
        this.lookAt = lookAt;
    }

    public String getStyleUrl() {
        return styleUrl;
    }

    public void setStyleUrl(String styleUrl) {
        this.styleUrl = styleUrl;
    }

    public KmlRouteLineString getLineString() {
        return lineString;
    }

    public void setLineString(KmlRouteLineString lineString) {
        this.lineString = lineString;
    }
}
