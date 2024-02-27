package sk.sfabian;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.model.source.KmlData;
import sk.sfabian.model.source.KmlFlyTo;
import sk.sfabian.model.target.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) {
            System.out.println("Hello World");
            try {
                File file = new File("src/main/resources/Test2withoutdocumetn.kml");
                Serializer serializer = new Persister();
                KmlData kml = serializer.read(KmlData.class, file);

                if (kml != null && kml.getTour() != null && kml.getTour().getPlaylist() != null
                        && kml.getTour().getPlaylist().getFlyTos() != null && !kml.getTour().getPlaylist().getFlyTos().isEmpty()) {
                    KmlRouteData routeData = new KmlRouteData();

                    //parametricky konstruktor nastavi open na 1 a inicializuje StyleMap, Tour a Placemarks
                    KmlRouteDocument routeDocument = new KmlRouteDocument("testicek");
                    //nastavenie default stylov trasy
                    routeDocument.initStyles(true, "99ffac59","6");
                    routeDocument.initStyles(false,"99ffac59","8");

                    //v tomto bode mame v dokumente inicializovany gx:playlist s prazdnym zoznamom elementov gx:Flyto
                    //nastavime synchronny pohyb kamery s pohybom modelu




                    KmlRoutePlacemark routePlacemark = new KmlRoutePlacemark();
                    routePlacemark.setName("testicekPlacemark");
                    routePlacemark.setDescription("");
                    routePlacemark.setStyleUrl("#m_ylw-pushpin");

                    KmlRouteLookAt lookAt = new KmlRouteLookAt();
                    KmlFlyTo firstFlyTo = kml.getTour().getPlaylist().getFlyTos().get(0);
                    lookAt.setAltitude(Double.valueOf(firstFlyTo.getCamera().getAltitude()));
                    lookAt.setHeading(Double.valueOf(firstFlyTo.getCamera().getHeading()));
                    lookAt.setLatitude(Double.valueOf(firstFlyTo.getCamera().getLatitude()));
                    lookAt.setRange(1500.0);
                    lookAt.setTilt(Double.valueOf(firstFlyTo.getCamera().getTilt()));
                    lookAt.setLongitude(Double.valueOf(firstFlyTo.getCamera().getLongitude()));
                    lookAt.setAltitudeMode(firstFlyTo.getCamera().getAltitudeMode());
                    routePlacemark.setLookAt(lookAt);

                    KmlRouteLineString lineString = new KmlRouteLineString();
                    lineString.setTessellate(1);
                    lineString.setAltitudeMode("relativeToGround");

                    StringBuilder coordinates = new StringBuilder();
                    for (KmlFlyTo flyTo : kml.getTour().getPlaylist().getFlyTos()) {
                        coordinates.append(flyTo.getCamera().getLongitude() + "," + flyTo.getCamera().getLatitude() + "," + flyTo.getCamera().getAltitude() + " ");
                    }

                    lineString.setCoordinates(coordinates.toString());
                    routePlacemark.setLineString(lineString);

                    List<KmlRoutePlacemark> routePlacemarks = new ArrayList<>();
                    routePlacemarks.add(routePlacemark);
                    routeDocument.setPlacemarks(routePlacemarks);

                    routeData.setDocument(routeDocument);

                    try {
                        Persister persister = new Persister();
                        File outputFile = new File("src/main/resources/output.kml");
                        persister.write(routeData, outputFile);
                        System.out.println("XML document created successfully.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
