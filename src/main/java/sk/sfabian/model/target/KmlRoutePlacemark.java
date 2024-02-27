package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Placemark")
public class KmlRoutePlacemark {
    @Element
    private String name;

    @Element
    private String description;

    @Element(name = "LookAt", type = KmlRouteLookAt.class)
    private KmlRouteLookAt lookAt;

    @Element
    private String styleUrl;

    @Element(name = "LineString", type = KmlRouteLineString.class)
    private KmlRouteLineString lineString;

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
