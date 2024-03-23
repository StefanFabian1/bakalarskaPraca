package sk.sfabian.export_module.model;

import org.simpleframework.xml.core.Persister;
import sk.sfabian.CustomProperties;
import sk.sfabian.export_module.model.source.ConvertedData;
import sk.sfabian.export_module.model.target.KmlRouteData;
import sk.sfabian.export_module.model.target.KmlRouteDocument;
import sk.sfabian.export_module.model.target.KmlRoutePlacemark;
import sk.sfabian.export_module.model.target.tour.KmlRouteFlyTo;
import sk.sfabian.export_module.model.target.tour.KmlRouteLookAt;
import sk.sfabian.export_module.model.target.tour.KmlRouteTimeSpan;
import sk.sfabian.export_module.model.target.track.KmlRouteTrack;
import sk.sfabian.export_module.model.target.track.kml_model.KmlRouteModel;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProcessOutput {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
    private static LocalDateTime actualFlyTime = null;
    public KmlRouteData process(List<ConvertedData> convertedDataList) {
        //TODO model bude iba String nie cela cesta, bude pribaleny vo vystupnom kmz
        String modelName = "C:\\bc\\Red_low_poly_car.dae";

        KmlRouteData routeData = new KmlRouteData();

        //parametricky konstruktor nastavi open na 1 a inicializuje StyleMap, Tour a Placemarks
        KmlRouteDocument routeDocument = new KmlRouteDocument(CustomProperties.documentName);

        //nastavenie stylov ciary
        routeDocument.initStyles(true, CustomProperties.lineColor,String.valueOf(CustomProperties.lineSize));
        routeDocument.initStyles(false,CustomProperties.lineColor,String.valueOf(CustomProperties.lineSize + (CustomProperties.lineSize / 3)));

        //inicaializacia objektu track
        KmlRouteTrack track = new KmlRouteTrack();
        track.setModel(new KmlRouteModel(modelName));

        //v konstruktore sme inicializovali gx:playlist s prazdnym zoznamom elementov gx:Flyto
        //naplnime target flyTos
        List<KmlRouteFlyTo> targetFlyTos = routeDocument.getTour().getPlaylist().getFlyTos();

        //KmlRouteFlyTo ma nainicializovane udaje gx:flyToMode a v KmlRouteLookAt horizonFow, altitudeMode nastavime globalne podla prveho prvku
        KmlRouteLookAt.setAltitudeMode(convertedDataList.get(0).getAltitudeMode());

        //ak prvy udaj v ConvertedData neobsahuje casovy udaj jedna sa o inicializaciu pohladu
        if (convertedDataList.get(0).getDuration() == null) {
            KmlRouteFlyTo initialFlyTo = new KmlRouteFlyTo();
            initialFlyTo.getLookAt().setLongitude(convertedDataList.get(0).getLongitude());
            initialFlyTo.getLookAt().setLatitude(convertedDataList.get(0).getLatitude());
            initialFlyTo.getLookAt().setAltitude(convertedDataList.get(0).getAltitude());
            initialFlyTo.getLookAt().setHeading(convertedDataList.get(0).getHeading());
            initialFlyTo.getLookAt().setTilt(convertedDataList.get(0).getTilt());
            initialFlyTo.getLookAt().setRange(CustomProperties.cameraDistance);
            targetFlyTos.add(initialFlyTo);
        }
        for (ConvertedData data : convertedDataList) {
            if (data.getDuration() != null) {
                KmlRouteFlyTo flyTo = new KmlRouteFlyTo();
                flyTo.setDuration(String.valueOf(data.getDuration()));
                flyTo.getLookAt().setTimeSpan(calculateTimeSpan(data));
                flyTo.getLookAt().setLongitude(data.getLongitude());
                flyTo.getLookAt().setLatitude(data.getLatitude());
                flyTo.getLookAt().setAltitude(data.getAltitude());
                flyTo.getLookAt().setHeading(data.getHeading()-15);
                flyTo.getLookAt().setTilt(data.getTilt()-30);
                flyTo.getLookAt().setRange(CustomProperties.cameraDistance);
                targetFlyTos.add(flyTo);
                track.getWhenList().add(formatter.format(actualFlyTime));
                track.getCoordList().add(data.getLongitude() + " " + data.getLatitude() + " " + data.getAltitude());
                track.getAnglesList().add((data.getHeading() + 0) + " " + (data.getTilt() + 90)*(-1) + " " + (-data.getRoll()));
            }
        }
        KmlRoutePlacemark routePlacemark = new KmlRoutePlacemark(routeDocument.getName(), track);

        List<KmlRoutePlacemark> routePlacemarks = new ArrayList<>();
        routePlacemarks.add(routePlacemark);
        routeDocument.setPlacemarks(routePlacemarks);

        routeData.setDocument(routeDocument);

        return routeData;
    }

    private KmlRouteTimeSpan calculateTimeSpan(ConvertedData data) {
        long nanosToAdd = (long) (data.getDuration() * 10000000000L);
        if (actualFlyTime == null) {
            actualFlyTime = data.getStartTime().plusNanos(nanosToAdd);
        } else {
            actualFlyTime = actualFlyTime.plusNanos(nanosToAdd);
        }
        return new KmlRouteTimeSpan(data.getStartTime(), actualFlyTime);
    }

    public void writeFile(KmlRouteData routeData, String path) {
        try {
            Persister persister = new Persister();
            File outputFile = new File(path);
            persister.write(routeData, outputFile);
            System.out.println("XML document created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
