package sk.sfabian.model.source;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gx:Tour")
public class KmlTour {
    @Element
    private String name;

    @Element(name = "Playlist", type = KmlPlaylist.class)
    private KmlPlaylist playlist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KmlPlaylist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(KmlPlaylist playlist) {
        this.playlist = playlist;
    }
}
