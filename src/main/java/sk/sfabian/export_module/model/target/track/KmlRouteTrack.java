package sk.sfabian.export_module.model.target.track;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import sk.sfabian.export_module.model.target.track.kml_model.KmlRouteModel;

import java.util.ArrayList;
import java.util.List;

@Root(name = "gx:Track")
public class KmlRouteTrack {
    @Element
    private String altitudeMode = "relativeToSeaFloor";
    @ElementList(inline = true, entry = "when", type = String.class, required = false)
    private List<String> whenList = new ArrayList<>();
    @ElementList(inline = true, entry = "gx:coord", type = String.class, required = false)
    private List<String> coordList = new ArrayList<>();
    @ElementList(inline = true, entry = "gx:angles", type = String.class, required = false)
    private List<String> anglesList = new ArrayList<>();
    @Element(name = "Model")
    private KmlRouteModel model;
    public String getAltitudeMode() {
        return altitudeMode;
    }

    public void setAltitudeMode(String altitudeMode) {
        this.altitudeMode = altitudeMode;
    }

    public List<String> getWhenList() {
        return whenList;
    }

    public void setWhenList(List<String> whenList) {
        this.whenList = whenList;
    }

    public List<String> getCoordList() {
        return coordList;
    }

    public void setCoordList(List<String> coordList) {
        this.coordList = coordList;
    }

    public KmlRouteModel getModel() {
        return model;
    }

    public void setModel(KmlRouteModel model) {
        this.model = model;
    }

    public List<String> getAnglesList() {
        return anglesList;
    }

    public void setAnglesList(List<String> anglesList) {
        this.anglesList = anglesList;
    }
}
