package sk.sfabian.import_module.kml_tour;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.CustomProperties;
import sk.sfabian.export_module.model.source.ConvertedData;
import sk.sfabian.import_module.ImportModuleException;
import sk.sfabian.import_module.ProcessInput;
import sk.sfabian.import_module.ProcessInputResult;
import sk.sfabian.import_module.kml_tour.model.KmlCamera;
import sk.sfabian.import_module.kml_tour.model.KmlData;
import sk.sfabian.import_module.kml_tour.model.KmlFlyTo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessKmlTourInput extends ProcessInput {
    private static LocalDateTime flyStartTime = LocalDateTime.now();

    //TODO validate() and return errors
    @Override
    public ProcessInputResult convertInput(File file) throws ImportModuleException {
        List<ConvertedData> result = new ArrayList<>();
        boolean isRollData = false;
        try {
            Serializer serializer = new Persister();
            KmlData kml = serializer.read(KmlData.class, file);
            validate(kml);
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
                    convertedData.setHeading((camera.getHeading() != null && !camera.getHeading().equals("")) ? Double.parseDouble(camera.getHeading()) : 0.0);
                    convertedData.setTilt((camera.getTilt() != null && !camera.getTilt().equals("")) ? Double.parseDouble(camera.getTilt()) : 0.0);
                    convertedData.setRoll((camera.getRoll() != null && !camera.getRoll().equals("")) ? Double.parseDouble(camera.getRoll()) : 0.0);
                    convertedData.setAltitudeMode(camera.getAltitudeMode());
                    if (convertedData.getTilt() > 0 || convertedData.getRoll() > 0) {
                        isRollData = true;
                    }
                    result.add(convertedData);
                }
            }
        } catch (Exception e) {
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Nerozpoznaná štruktúra kml súboru");
        }
        return new ProcessInputResult("Načítaná virtuálna prehliadka kml s objektom camera " + (isRollData ? "s náklonom" : "bez náklonu"), result);
    }

    private void validate(KmlData kml) throws ImportModuleException {
        if (kml == null) {
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Kml súbor je prázdny");
        }
        if (kml.getTour() == null) {
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Kml súbor neobsahuje objekt gx:Tour");
        }
        if (kml.getTour().getPlaylist() == null) {
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Kml objekt gx:Tour neobsahuje žiaden Playlist");
        }
        if (kml.getTour().getPlaylist().getFlyTos() == null || kml.getTour().getPlaylist().getFlyTos().isEmpty()) {
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Playlist objektu gx:Tour neobsahuje dáta FlyTo");
        }
    }
}
