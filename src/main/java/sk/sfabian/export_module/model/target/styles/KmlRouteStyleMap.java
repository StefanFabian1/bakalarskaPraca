package sk.sfabian.export_module.model.target.styles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "StyleMap")
public class KmlRouteStyleMap {
    @Attribute(name = "id")
    private String id;
    @ElementList(inline = true, entry = "Pair", type = KmlRoutePair.class, required = false)
    private List<KmlRoutePair> pair;

    public void init() {
        this.id = "multiTrack";
        this.pair = new ArrayList<>();
        this.pair.add(new KmlRoutePair("normal", "#multiTrack_n"));
        this.pair.add(new KmlRoutePair("highlight", "#multiTrack_h"));
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<KmlRoutePair> getPair() {
        return pair;
    }

    public void setPair(List<KmlRoutePair> pair) {
        this.pair = pair;
    }
}
@Root(name = "Pair")
class KmlRoutePair {
    @Element
    private String key;
    @Element
    private String styleUrl;

    public KmlRoutePair() {
    }
    public KmlRoutePair(String key, String styleUrl){
        this.key = key;
        this.styleUrl = styleUrl;
    };
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStyleUrl() {
        return styleUrl;
    }

    public void setStyleUrl(String styleUrl) {
        this.styleUrl = styleUrl;
    }
}