package sk.sfabian.import_module;

import sk.sfabian.import_module.kml_tour.ProcessKmlTourInput;

import java.io.File;

public class ProcessInputFactory {

        public ProcessInput createProcessInput(File file) {
            //TODO kontrola ci subor obsahuje pozadovanu strukturu - v nasom pripade mame pripraveny mapping teda asi try/catch pri parsovani??
            if (file.getName().endsWith(".kml")) {
                return new ProcessKmlTourInput();
            } else {
                //TODO podpora pre kmz, gpx, ...
                return null;
            }
        }

}
