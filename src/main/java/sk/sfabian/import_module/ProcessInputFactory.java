package sk.sfabian.import_module;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.import_module.kml_tour.ProcessKmlTourInput;
import sk.sfabian.import_module.kml_tour.model.KmlData;

import java.io.File;

public class ProcessInputFactory {

    public ProcessInput createProcessInput(File file) throws ImportModuleException {
        if (file.getName().endsWith(".kml")) {
            boolean wasError = true;
            try {
                Serializer serializer = new Persister();
                serializer.read(KmlData.class, file);
                return new ProcessKmlTourInput();
            } catch (Exception ignored) {
            }
            //ak neboli splnene podmienky pre ziadnu z implementacii (nedoslo k return) - vyhodim exception
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.KML_STRUCTURE_NOT_RECOGNIZED, "Nerozpoznaná štruktúra kml súboru");
        } else {
            //TODO podpora pre kmz, gpx, ...
            throw new ImportModuleException(ImportModuleException.ImportModuleExceptionType.UNSUPPORTED_FILE_FORMAT, "Nepodporovaný formát súboru");
        }
    }
}
