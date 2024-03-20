package sk.sfabian.export_module.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import sk.sfabian.export_module.model.target.track.KmlRouteTrack;

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

    public String getStyleUrl() {
        return styleUrl;
    }

    public void setStyleUrl(String styleUrl) {
        this.styleUrl = styleUrl;
    }
    public KmlRouteTrack getTrack() {
        return track;
    }

    public void setTrack(KmlRouteTrack track) {
        this.track = track;
    }
}
