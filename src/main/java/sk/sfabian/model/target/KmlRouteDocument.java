package sk.sfabian.model.target;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Document")
public class KmlRouteDocument {

    @Element
    private String name;

    @Element
    private int open;

    @ElementList(inline = true, entry = "Style", type = KmlRouteStyle.class, required = false)
    private List<KmlRouteStyle> styles;

    @ElementList(inline = true, entry = "Placemark", type = KmlRoutePlacemark.class, required = false)
    private List<KmlRoutePlacemark> placemarks;

    @Element
    private KmlRouteStyleMap styleMap;

    public KmlRouteDocument() {}

    public KmlRouteDocument(String name) {
        this.name = name;
        this.open = 1;
        this.styles = new ArrayList<>();
        this.placemarks = new ArrayList<>();
        this.styleMap = new KmlRouteStyleMap();
        this.styleMap.init();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public List<KmlRouteStyle> getStyles() {
        return styles;
    }

    public void setStyles(List<KmlRouteStyle> styles) {
        this.styles = styles;
    }

    public List<KmlRoutePlacemark> getPlacemarks() {
        return placemarks;
    }

    public void setPlacemarks(List<KmlRoutePlacemark> placemarks) {
        this.placemarks = placemarks;
    }

    public KmlRouteStyleMap getStyleMap() {
        return styleMap;
    }

    public void setStyleMap(KmlRouteStyleMap styleMap) {
        this.styleMap = styleMap;
    }
}

