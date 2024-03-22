package sk.sfabian.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sk.sfabian.CustomProperties;
import sk.sfabian.export_module.model.ProcessOutput;
import sk.sfabian.export_module.model.source.ConvertedData;
import sk.sfabian.export_module.model.target.KmlRouteData;
import sk.sfabian.import_module.ProcessInput;
import sk.sfabian.import_module.ProcessInputFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.prefs.Preferences;

public class ApplicationGui extends Application {
    private static final String LAST_USED_FOLDER_KEY = "last_used_folder";
    private static final String LAST_USED_EXPORT_FOLDER_KEY = "last_used_export_folder";
    private Preferences prefs = Preferences.userNodeForPackage(ApplicationGui.class);
    private List<ConvertedData> convertedDataList;

    @FXML
    private TextField sourcePath;
    @FXML
    private Label importModuleStatus;
    @FXML
    private Pane exportModulPane;
    @FXML
    private TextField targetFileName;
    @FXML
    private TextField targetFolderPath;
    @FXML
    private Button convertButton;
    @FXML
    private ColorPicker lineColorPicker;
    @FXML
    private TextField documentName;
    @FXML
    private TextField cameraDistance;
    @FXML
    private Label exportModuleStatus;
    @FXML
    private ComboBox<Integer> lineSize;
    @Override
    public void start(Stage primaryStage) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gui/applicationGui.fxml");
            if (inputStream == null) {
                throw new FileNotFoundException("FXML file not found");
            }
            Parent root = new FXMLLoader().load(inputStream);
            primaryStage.setTitle("Vizualizácia dát v aplikácii Google Earth");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        String lastUsedFolder = prefs.get(LAST_USED_FOLDER_KEY, null);
        if (lastUsedFolder != null) {
            fileChooser.setInitialDirectory(new File(lastUsedFolder));
        } else {
            String documentsDirectory = null;
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                documentsDirectory = System.getenv("USERPROFILE") + "\\Documents";
            } else if (os.contains("mac")) {
                documentsDirectory = System.getProperty("user.home") + "/Documents";
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                documentsDirectory = System.getProperty("user.home") + "/Documents";
            } else if (os.contains("sunos")) {
                documentsDirectory = System.getProperty("user.home") + "/Documents";
            }
            if (documentsDirectory != null) {
                File initialDirectory = new File(documentsDirectory);
                fileChooser.setInitialDirectory(initialDirectory);
            }
        }
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.csv"),
                new FileChooser.ExtensionFilter("KML Files (*.kml)", "*.kml")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            startImportModule(selectedFile);
        } else {
            exportModulPane.setVisible(false);
        }
    }
    private void startImportModule(File selectedFile) {
        prefs.put(LAST_USED_FOLDER_KEY, selectedFile.getParent());
        sourcePath.setText(selectedFile.getAbsolutePath());
        ProcessInput input = new ProcessInputFactory().createProcessInput(selectedFile);
        convertedDataList = input.convertInput(selectedFile);
        if (convertedDataList != null && !convertedDataList.isEmpty()) {
            importModuleStatus.setTextFill(Paint.valueOf("#5636f5"));
            importModuleStatus.setText("Súbor úspešne načítaný");
            exportModulPane.setVisible(true);
            documentName.setPromptText(CustomProperties.documentName);
            cameraDistance.setPromptText("1600.0");
            lineSize.getItems().addAll(4,6,8,10,12);
            lineSize.setValue(6);
            CustomProperties.lineColor = "#00dbf8";
        } else {
            importModuleStatus.setTextFill(Paint.valueOf("#ff2626"));
            //TODO error z nacitania
            importModuleStatus.setText("Nie je vybraný žiaden súbor !");
            exportModulPane.setVisible(false);
        }
    }

    public void setTargetPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Target Folder");
        String lastUsedExportFolder = prefs.get(LAST_USED_EXPORT_FOLDER_KEY, null);
        if (lastUsedExportFolder != null) {
            directoryChooser.setInitialDirectory(new File(lastUsedExportFolder));
        } else {
            directoryChooser.setInitialDirectory(new File(prefs.get(LAST_USED_FOLDER_KEY, null)));
        }
        File selectedFolder = directoryChooser.showDialog(new Stage());

        if (selectedFolder != null) {
            String selectedFolderPath = selectedFolder.getAbsolutePath();
            prefs.put(LAST_USED_EXPORT_FOLDER_KEY, selectedFolderPath);
            targetFolderPath.setText(selectedFolderPath);
            convertButton.setDisable(false);
            CustomProperties.pathToTarget = selectedFolderPath;
        } else {
            convertButton.setDisable(true);
        }
    }

    public void startExport() {
        if (convertedDataList != null && !convertedDataList.isEmpty()) {
            if (documentName != null && !documentName.equals("")) {
                CustomProperties.documentName = documentName.getText();
            }
            if (cameraDistance.getText() != null && !cameraDistance.getText().equals("")) {
                try {
                    Double.valueOf(cameraDistance.getText());
                } catch (NumberFormatException exception) {
                    exportModuleStatus.setText("Vzdialenosť kamery musí byť kladné číslo !");
                    return;
                }
                if (Double.valueOf(cameraDistance.getText()) < 1) {
                    exportModuleStatus.setText("Vzdialenosť kamery musí byť kladné číslo !");
                    return;
                }
                CustomProperties.cameraDistance = Double.valueOf(cameraDistance.getText());
            } else {
                CustomProperties.cameraDistance = Double.valueOf(cameraDistance.getPromptText());
            }
            if (lineSize.getValue() != null && lineSize.getValue().intValue() > 0) {
                CustomProperties.lineSize = lineSize.getValue();
            } else {
                exportModuleStatus.setText("Hrúbka čiary musí byť celé kladné číslo !");
                return;
            }
            CustomProperties.pathToTarget = CustomProperties.pathToTarget + "\\" + ((targetFileName.getText() == null || targetFileName.getText().equals("")) ? "output" : targetFileName.getText()) + ".kml";
            ProcessOutput output = new ProcessOutput();
            KmlRouteData outputData = output.process(convertedDataList);
            output.writeFile(outputData, CustomProperties.pathToTarget);
        }
    }

    public void pickColor() {
        CustomProperties.lineColor = toHexString(lineColorPicker.getValue());
    }
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
