package sk.sfabian.model.source;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "gx:FlyTo", strict = false)
public class KmlFlyTo implements KmlElement {
    @Element(name = "Camera", type = KmlCamera.class)
    private KmlCamera camera;

    public KmlCamera getCamera() {
        return camera;
    }

    public void setCamera(KmlCamera camera) {
        this.camera = camera;
    }
}
