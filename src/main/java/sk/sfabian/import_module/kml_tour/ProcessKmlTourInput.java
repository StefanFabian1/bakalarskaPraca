package sk.sfabian.import_module.kml_tour;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.CustomProperties;
import sk.sfabian.export_module.model.source.ConvertedData;
import sk.sfabian.import_module.ProcessInput;
import sk.sfabian.import_module.kml_tour.model.KmlCamera;
import sk.sfabian.import_module.kml_tour.model.KmlData;
import sk.sfabian.import_module.kml_tour.model.KmlFlyTo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProcessKmlTourInput extends ProcessInput {
    private static LocalDateTime flyStartTime = LocalDateTime.now();
    //TODO validate() and return errors
    @Override
    public List<ConvertedData> convertInput(File file) {
        List<ConvertedData> result = new ArrayList<>();
        try {
            Serializer serializer = new Persister();
            KmlData kml = serializer.read(KmlData.class, file);
            if (kml != null && kml.getTour() != null && kml.getTour().getPlaylist() != null
                    && kml.getTour().getPlaylist().getFlyTos() != null && !kml.getTour().getPlaylist().getFlyTos().isEmpty()) {
                CustomProperties.documentName = kml.getTour().getName();
                for (KmlFlyTo flyTo : kml.getTour().getPlaylist().getFlyTos()) {
                    if (flyTo.getCamera() != null) {
                        ConvertedData convertedData = new ConvertedData();
                        convertedData.setDuration(flyTo.getDuration() == null ? null : Double.valueOf(flyTo.getDuration()));
                        convertedData.setStartTime(flyStartTime);
                        KmlCamera camera = flyTo.getCamera();
                        convertedData.setLongitude(Double.valueOf(camera.getLongitude()));
                        convertedData.setLatitude(Double.valueOf(camera.getLatitude()));
                        convertedData.setAltitude(Double.valueOf(camera.getAltitude()));
                        convertedData.setHeading(Double.valueOf(camera.getHeading()));
                        convertedData.setTilt(Double.valueOf(camera.getTilt()));
                        convertedData.setRoll(Double.valueOf(camera.getRoll()));
                        convertedData.setAltitudeMode(camera.getAltitudeMode());
                        result.add(convertedData);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
