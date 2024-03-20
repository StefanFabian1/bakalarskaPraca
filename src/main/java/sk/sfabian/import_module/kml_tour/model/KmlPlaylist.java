package sk.sfabian.import_module.kml_tour.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "gx:Playlist",  strict = false)
public class KmlPlaylist {

    @ElementList(inline = true, entry = "FlyTo", type = KmlFlyTo.class)
    private List<KmlFlyTo> flyTos;

    @ElementList(inline = true, entry = "Wait", type = KmlWait.class)
    private List<KmlWait> waits;

    public List<KmlFlyTo> getFlyTos() {
        return flyTos;
    }

    public void setFlyTos(List<KmlFlyTo> flyTos) {
        this.flyTos = flyTos;
    }

    public List<KmlWait> getWaits() {
        return waits;
    }

    public void setWaits(List<KmlWait> waits) {
        this.waits = waits;
    }
}
