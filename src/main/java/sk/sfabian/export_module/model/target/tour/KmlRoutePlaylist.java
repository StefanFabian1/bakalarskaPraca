package sk.sfabian.export_module.model.target.tour;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;
@Root(name = "gx:Playlist",  strict = false)
public class KmlRoutePlaylist {

    @ElementList(inline = true, entry = "gx:FlyTo", type = KmlRouteFlyTo.class)
    private List<KmlRouteFlyTo> flyTos;

    public KmlRoutePlaylist() {
        this.flyTos = new ArrayList<>();
    }

    public List<KmlRouteFlyTo> getFlyTos() {
        return flyTos;
    }

    public void setFlyTos(List<KmlRouteFlyTo> flyTos) {
        this.flyTos = flyTos;
    }
}


