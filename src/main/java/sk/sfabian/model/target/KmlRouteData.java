package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "kml")
public class KmlRouteData {
    @Element(name = "Document", type = KmlRouteDocument.class)
    private KmlRouteDocument document;

    public KmlRouteDocument getDocument() {
        return document;
    }

    public void setDocument(KmlRouteDocument document) {
        this.document = document;
    }
}
