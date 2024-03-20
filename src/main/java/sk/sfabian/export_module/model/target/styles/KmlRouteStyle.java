package sk.sfabian.export_module.model.target.styles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Style")
public class KmlRouteStyle {
    @Attribute(name = "id")
    private String id;
    @Element(name = "ListStyle", type = KmlRouteListStyle.class)
    private KmlRouteListStyle listStyle;

    @Element(name = "IconStyle", type = KmlRouteIconStyle.class)
    private KmlRouteIconStyle iconStyle;

    @Element(name = "BalloonStyle", type = KmlRouteBalloonStyle.class)
    private KmlRouteBalloonStyle balloonStyle;

    @Element(name = "LineStyle", type = KmlRouteLineStyle.class)
    private KmlRouteLineStyle lineStyle;

    public KmlRouteStyle() {
    }

    public KmlRouteStyle(String id, String lineColor, String lineSize) {
        this.id = id;
        //default konstruktor nastavi prazdnu ikonu a ballon, pouzivame model
        this.iconStyle = new KmlRouteIconStyle();
        this.balloonStyle = new KmlRouteBalloonStyle();
        //list style nebudem pouzivat, necham prazdny
        this.listStyle = new KmlRouteListStyle();
        this.lineStyle = new KmlRouteLineStyle(lineColor, lineSize);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KmlRouteListStyle getListStyle() {
        return listStyle;
    }

    public void setListStyle(KmlRouteListStyle listStyle) {
        this.listStyle = listStyle;
    }

    public KmlRouteIconStyle getIconStyle() {
        return iconStyle;
    }

    public void setIconStyle(KmlRouteIconStyle iconStyle) {
        this.iconStyle = iconStyle;
    }

    public KmlRouteBalloonStyle getBalloonStyle() {
        return balloonStyle;
    }

    public void setBalloonStyle(KmlRouteBalloonStyle balloonStyle) {
        this.balloonStyle = balloonStyle;
    }

    public KmlRouteLineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(KmlRouteLineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

}

@Root(name = "IconStyle")
class KmlRouteIconStyle {

    @Element(name = "Icon")
    private String icon;

    public KmlRouteIconStyle() {
        this.icon = "";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

@Root(name = "BalloonStyle")
class KmlRouteBalloonStyle {
    @Element
    private String displayMode = "hide";

    public KmlRouteBalloonStyle() {}

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }
}

@Root(name = "LineStyle")
class KmlRouteLineStyle {
    @Element
    private String color;

    @Element
    private String width;

    public KmlRouteLineStyle() {

    }

    public KmlRouteLineStyle(String color, String width) {
        this.color = color;
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
