package sk.sfabian;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.business.DataProcessHelper;
import sk.sfabian.model.source.KmlData;
import sk.sfabian.model.source.KmlFlyTo;
import sk.sfabian.model.target.*;
import sk.sfabian.model.target.tour.KmlRouteFlyTo;
import sk.sfabian.model.target.tour.KmlRouteLookAt;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
        public static void main(String[] args) {
            System.out.println("Hello World");
            try {
                File file = new File("src/main/resources/Test2withoutdocumetn.kml");
                Serializer serializer = new Persister();
                KmlData kml = serializer.read(KmlData.class, file);

                //TODO vlastna class, bez mappingu na KML subor len s pozadovanymi udajmi... z classy budem citat data
                //TODO interface pre naplnenie classy
                //TODO implementacia pre kml bez dokumnetu
                //TODO implementacia pre KML v dokumente, KMZ
                //TODO implementacia bez mapovania, respektive pre gps data GPX a podobne
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
                    List<KmlFlyTo> sourceFlyTos = kml.getTour().getPlaylist().getFlyTos();
                    List<KmlRouteFlyTo> targetFlyTos = routeDocument.getTour().getPlaylist().getFlyTos();
                    //prejdeme vsetky flyTos zo source, upravime a pridame do target
                    //Momentalne ako start timestampu zadame aktualny systemovy cas, v buducnosti sa moze citat z atributu alebo zadat na vstupe
                    for (KmlFlyTo sourceFlyTo : sourceFlyTos) {
                        if (sourceFlyTo.getDuration() != null) {
                            targetFlyTos.add(DataProcessHelper.processFlyTo(sourceFlyTo));
                        }
                    }
                    KmlRoutePlacemark routePlacemark = new KmlRoutePlacemark(routeDocument.getName(), DataProcessHelper.getTrack());
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
