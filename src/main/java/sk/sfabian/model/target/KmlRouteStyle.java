package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Style")
public class KmlRouteStyle {
    @Element(name = "ListStyle", type = KmlRouteListStyle.class)
    private KmlRouteListStyle listStyle;

    public KmlRouteListStyle getListStyle() {
        return listStyle;
    }

    public void setListStyle(KmlRouteListStyle listStyle) {
        this.listStyle = listStyle;
    }
}
