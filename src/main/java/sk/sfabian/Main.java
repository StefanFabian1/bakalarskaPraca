package sk.sfabian;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sk.sfabian.business.DataProcessHelper;
import sk.sfabian.export_module.model.ProcessOutput;
import sk.sfabian.export_module.model.source.ConvertedData;
import sk.sfabian.export_module.model.target.KmlRouteData;
import sk.sfabian.export_module.model.target.KmlRouteDocument;
import sk.sfabian.export_module.model.target.KmlRoutePlacemark;
import sk.sfabian.import_module.ProcessInput;
import sk.sfabian.import_module.ProcessInputFactory;
import sk.sfabian.import_module.kml_tour.model.KmlData;
import sk.sfabian.import_module.kml_tour.model.KmlFlyTo;
import sk.sfabian.export_module.model.target.tour.KmlRouteFlyTo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) {
            System.out.println("Hello World");
            try {
                //TODO to bude volanie na GUI
                //TODO z GUI sa natiahne File, zatial takto:
                File file = new File("C:/bc/testHavaria.kml");
                ProcessInput input = new ProcessInputFactory().createProcessInput(file);
                List<ConvertedData> convertedDataList = input.convertInput(file);
                //TODO spracujeme data na kml a zapiseme vysledok
                ProcessOutput output = new ProcessOutput();
                KmlRouteData outputData = output.process(convertedDataList);
                output.writeFile(outputData, "src/main/resources/output.kml");
/*
                //File file = new File("src/main/resources/Test2withoutdocumetn.kml");


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
*/

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

}
