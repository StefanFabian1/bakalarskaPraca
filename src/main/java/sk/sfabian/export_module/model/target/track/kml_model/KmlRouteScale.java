package sk.sfabian.export_module.model.target.track.kml_model;

import org.simpleframework.xml.Element;

public class KmlRouteScale {
    @Element
    private String x;
    @Element
    private String y;
    @Element
    private String z;
    public KmlRouteScale(){}
    public KmlRouteScale(double scale) {
        this.x = String.valueOf(scale);
        this.y = String.valueOf(scale);
        this.z = String.valueOf(scale);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }
}
