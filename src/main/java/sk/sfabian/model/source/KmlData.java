package sk.sfabian.model.source;

import org.simpleframework.xml.*;

@Root(name = "kml")
public class KmlData {
    @Element(name = "Tour", type = KmlTour.class)
    private KmlTour tour;

    public KmlTour getTour() {
        return tour;
    }
    public void setTour(KmlTour tour) {
        this.tour = tour;
    }
}
