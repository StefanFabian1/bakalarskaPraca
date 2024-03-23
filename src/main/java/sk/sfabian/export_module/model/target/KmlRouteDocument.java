package sk.sfabian.export_module.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import sk.sfabian.export_module.model.target.styles.KmlRouteStyle;
import sk.sfabian.export_module.model.target.styles.KmlRouteStyleMap;
import sk.sfabian.export_module.model.target.tour.KmlRouteGxTour;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Document")
public class KmlRouteDocument {

    @Element
    private String name;
    @Element
    private int open;
    @Element(name = "StyleMap")
    private KmlRouteStyleMap styleMap;
    @ElementList(inline = true, entry = "Style", type = KmlRouteStyle.class, required = false)
    private List<KmlRouteStyle> styles;
    @Element(name = "gx:Tour", type = KmlRouteGxTour.class)
    private KmlRouteGxTour tour;
    @ElementList(inline = true, entry = "Placemark", type = KmlRoutePlacemark.class, required = false)
    private List<KmlRoutePlacemark> placemarks;
    @Element


    public KmlRouteDocument() {}

    public KmlRouteDocument(String name) {
        this.name = name;
        this.open = 1;
        this.styles =  this.styles = new ArrayList<>();
        this.placemarks = new ArrayList<>();
        this.styleMap = new KmlRouteStyleMap();
        this.styleMap.init();
        this.tour = new KmlRouteGxTour(name + " tour");
    }
    public void initStyles(boolean normal, String lineColor, String lineSize) {
        //parametricky konstruktor nastavi iba potrebne udaje
        this.styles.add(new KmlRouteStyle(normal ? "multiTrack_n" : "multiTrack_h", lineColor, lineSize));
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
    public KmlRouteGxTour getTour() {
        return tour;
    }

    public void setTour(KmlRouteGxTour tour) {
        this.tour = tour;
    }
}

