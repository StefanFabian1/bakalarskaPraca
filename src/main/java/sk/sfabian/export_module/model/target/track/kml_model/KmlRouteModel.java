package sk.sfabian.export_module.model.target.track.kml_model;

import org.simpleframework.xml.Element;
import sk.sfabian.import_module.kml_tour.model.KmlFlyTo;

public class KmlRouteModel {
    @Element(name = "Location", required = false)
    private KmlRouteLocation location;
    @Element(name = "Orientation", required = false)
    private KmlRouteOrientation orientation;
    @Element(name = "Scale")
    private KmlRouteScale scale;
    @Element(name = "Link")
    private KmlRouteLink link;
    @Element(name = "ResourceMap")
    private String resourceMap;

    public KmlRouteModel() {}
    public KmlRouteModel(String name) {
        this.scale = new KmlRouteScale(6);
        this.link = new KmlRouteLink(name);
        this.resourceMap = "";
    }
    public KmlRouteLocation getLocation() {
        return location;
    }

    public void setLocation(KmlRouteLocation location) {
        this.location = location;
    }

    public KmlRouteOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(KmlRouteOrientation orientation) {
        this.orientation = orientation;
    }

    public KmlRouteScale getScale() {
        return scale;
    }

    public void setScale(KmlRouteScale scale) {
        this.scale = scale;
    }

    public KmlRouteLink getLink() {
        return link;
    }

    public void setLink(KmlRouteLink link) {
        this.link = link;
    }

    public String getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(String resourceMap) {
        this.resourceMap = resourceMap;
    }
}
