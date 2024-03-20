package sk.sfabian.business;

import sk.sfabian.import_module.kml_tour.model.KmlFlyTo;
import sk.sfabian.export_module.model.target.tour.KmlRouteFlyTo;
import sk.sfabian.export_module.model.target.track.kml_model.KmlRouteModel;
import sk.sfabian.export_module.model.target.tour.KmlRouteTimeSpan;
import sk.sfabian.export_module.model.target.track.KmlRouteTrack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProcessHelper {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
    private static LocalDateTime flyStartTime = LocalDateTime.now();
    private static LocalDateTime actualFlyTime = null;
    private static KmlRouteTrack track;

    public static KmlRouteFlyTo processFlyTo(KmlFlyTo source) {
        //KmlRouteFlyTo ma nainicializovane staticke udaje gx:flyToMode a v KmlRouteLookAt horizonFow a altitudeMode
        KmlRouteFlyTo flyTo = new KmlRouteFlyTo();
        if (source.getDuration() != null && source.getCamera() != null) {
            flyTo.setDuration(source.getDuration());
            Double durationDouble = Double.valueOf(source.getDuration());
            long nanosToAdd = (long) (durationDouble * 10000000000L);
            if (actualFlyTime == null) {
                actualFlyTime = flyStartTime.plusNanos(nanosToAdd);
                track = new KmlRouteTrack();
            } else {
                actualFlyTime = actualFlyTime.plusNanos(nanosToAdd);
            }

            track.setModel(new KmlRouteModel("C:\\bc\\Red_low_poly_car.dae"));
            track.getWhenList().add(formatter.format(actualFlyTime));
            track.getCoordList().add(source.getCamera().getLongitude() + " " + source.getCamera().getLatitude() + " " + source.getCamera().getAltitude());
            track.getAnglesList().add(source.getCamera().getHeading() + " " + (Double.valueOf(source.getCamera().getTilt()) - 90) + " " + source.getCamera().getRoll());
            flyTo.getLookAt().setTimeSpan(new KmlRouteTimeSpan(flyStartTime, actualFlyTime));
            flyTo.getLookAt().setLongitude(Double.valueOf(source.getCamera().getLongitude()));
            flyTo.getLookAt().setLatitude(Double.valueOf(source.getCamera().getLatitude()));
            flyTo.getLookAt().setAltitude(Double.valueOf(source.getCamera().getAltitude()));
            //TODO dynamicky heading ked zatocim tzn prepocitat z latitude
            flyTo.getLookAt().setHeading(-82.83);
            flyTo.getLookAt().setTilt(45);
            flyTo.getLookAt().setRange(1600);
            return flyTo;
        }
        return null;
    }

    public static KmlRouteTrack getTrack() {
        return track;
    }
}
