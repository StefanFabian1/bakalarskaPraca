package sk.sfabian.model.target;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "kml")
public class KmlRouteData {

    @Attribute(name = "xmlns")
    private String xmlns1 = "http://www.opengis.net/kml/2.2";
    @Attribute(name = "xmlns:gx")
    private String xmlns2 = "http://www.google.com/kml/ext/2.2";
    @Attribute(name = "xmlns:kml")
    private String xmlns3 = "http://www.opengis.net/kml/2.2";
    @Attribute(name = "xmlns:atom")
    private String xmlns4 = "http://www.w3.org/2005/Atom";
    @Element(name = "Document", type = KmlRouteDocument.class)
    private KmlRouteDocument document;

    public KmlRouteDocument getDocument() {
        return document;
    }

    public void setDocument(KmlRouteDocument document) {
        this.document = document;
    }

    public String getXmlns1() {
        return xmlns1;
    }

    public void setXmlns1(String xmlns1) {
        this.xmlns1 = xmlns1;
    }

    public String getXmlns2() {
        return xmlns2;
    }

    public void setXmlns2(String xmlns2) {
        this.xmlns2 = xmlns2;
    }

    public String getXmlns3() {
        return xmlns3;
    }

    public void setXmlns3(String xmlns3) {
        this.xmlns3 = xmlns3;
    }

    public String getXmlns4() {
        return xmlns4;
    }

    public void setXmlns4(String xmlns4) {
        this.xmlns4 = xmlns4;
    }
}
