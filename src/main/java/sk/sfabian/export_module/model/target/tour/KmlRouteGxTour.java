package sk.sfabian.export_module.model.target.tour;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gx:Tour")
public class KmlRouteGxTour {
    @Element
    private String name;

    @Element(name = "gx:Playlist", type = KmlRoutePlaylist.class)
    private KmlRoutePlaylist playlist;

    public KmlRouteGxTour() {}
    public KmlRouteGxTour(String name) {
        this.name = name;
        this.playlist = new KmlRoutePlaylist();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KmlRoutePlaylist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(KmlRoutePlaylist playlist) {
        this.playlist = playlist;
    }
}
