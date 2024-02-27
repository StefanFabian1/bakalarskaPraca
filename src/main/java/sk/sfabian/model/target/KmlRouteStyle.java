package sk.sfabian.model.target;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.swing.*;

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

    @Element
    private KmlRouteIcon icon;

    public KmlRouteIconStyle() {
        this.icon = new KmlRouteIcon();
    }

    public KmlRouteIcon getIcon() {
        return icon;
    }

    public void setIcon(KmlRouteIcon icon) {
        this.icon = icon;
    }
}

@Root(name = "Icon")
class KmlRouteIcon {

}

@Root(name = "BalloonStyle")
class KmlRouteBalloonStyle {

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
