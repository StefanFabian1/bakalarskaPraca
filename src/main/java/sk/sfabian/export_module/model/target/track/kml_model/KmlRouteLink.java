package sk.sfabian.export_module.model.target.track.kml_model;

import org.simpleframework.xml.Element;

public class KmlRouteLink {
    @Element
    private String href;
    public KmlRouteLink() {}
    public KmlRouteLink(String link) {
        this.href = link;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
