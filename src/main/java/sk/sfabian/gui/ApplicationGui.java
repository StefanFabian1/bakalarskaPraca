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
import sk.sfabian.export_module.model.target.KmlRouteData;
import sk.sfabian.import_module.ImportModuleException;
import sk.sfabian.import_module.ProcessInput;
import sk.sfabian.import_module.ProcessInputFactory;
import sk.sfabian.import_module.ProcessInputResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.Preferences;

public class ApplicationGui extends Application {
    private static final String LAST_USED_FOLDER_KEY = "last_used_folder";
    private static final String LAST_USED_EXPORT_FOLDER_KEY = "last_used_export_folder";
    private Preferences prefs = Preferences.userNodeForPackage(ApplicationGui.class);
    private ProcessInputResult processInputResult;

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
    private ComboBox<String> lineSize;
    @FXML
    private CheckBox selectShowTrack;
    @FXML
    private Pane trackSettingsPane;
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
    public void chooseFile() throws ImportModuleException {
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
            importModuleStatus.setTextFill(Paint.valueOf("#ff2626"));
            importModuleStatus.setText("Nie je vybraný žiaden súbor !");
        }
    }
    private void startImportModule(File selectedFile) throws ImportModuleException {
        prefs.put(LAST_USED_FOLDER_KEY, selectedFile.getParent());
        sourcePath.setText(selectedFile.getAbsolutePath());
        try {
            ProcessInput input = new ProcessInputFactory().createProcessInput(selectedFile);
            processInputResult = input.convertInput(selectedFile);
        } catch (ImportModuleException importException) {
            importModuleStatus.setTextFill(Paint.valueOf("#ff2626"));
            importModuleStatus.setText(importException.getMessage());
            exportModulPane.setVisible(false);
            importException.printStackTrace();
            return;
        }
        if (processInputResult != null && processInputResult.getConvertedData() != null && !processInputResult.getConvertedData().isEmpty()) {
            importModuleStatus.setTextFill(Paint.valueOf("#5636f5"));
            importModuleStatus.setText((processInputResult.getMessage() != null && !processInputResult.getMessage().equals("")) ? processInputResult.getMessage() : "Súbor úspešne načítaný");
            exportModulPane.setVisible(true);
            documentName.setPromptText(CustomProperties.documentName);
            cameraDistance.setPromptText("1600.0");
            lineSize.getItems().addAll("4", "6", "8", "10", "12");
            lineSize.setValue("6");
            CustomProperties.lineColor = "#bb00dbf8";
            CustomProperties.lineColorHighlighted = "#ff00dbf8";
        } else {
            importModuleStatus.setTextFill(Paint.valueOf("#ff2626"));
            importModuleStatus.setText("Nepodarilo sa spracovať súbor !");
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
            exportModuleStatus.setTextFill(Paint.valueOf("#5636f5"));
            exportModuleStatus.setText("");
        } else {
            CustomProperties.pathToTarget = null;
            exportModuleStatus.setTextFill(Paint.valueOf("#ff2626"));
            exportModuleStatus.setText("Musíte zvoliť cieľový priečinok !");
            targetFolderPath.setText("");
            convertButton.setDisable(true);
        }
    }

    public void startExport() {
        if (processInputResult != null && processInputResult.getConvertedData() != null && !processInputResult.getConvertedData().isEmpty()) {
            if (documentName != null && documentName.getText() != null && !documentName.getText().equals("")) {
                CustomProperties.documentName = documentName.getText();
            } else {
                CustomProperties.documentName = documentName.getPromptText();
            }
            if (cameraDistance.getText() != null && !cameraDistance.getText().equals("")) {
                try {
                    Double.valueOf(cameraDistance.getText());
                } catch (NumberFormatException exception) {
                    exportModuleStatus.setText("Vzdialenosť kamery musí byť kladné číslo !");
                    return;
                }
                if (Double.parseDouble(cameraDistance.getText()) < 1) {
                    exportModuleStatus.setText("Vzdialenosť kamery musí byť kladné číslo !");
                    return;
                }
                CustomProperties.cameraDistance = Double.valueOf(cameraDistance.getText());
            } else {
                CustomProperties.cameraDistance = Double.valueOf(cameraDistance.getPromptText());
            }
            boolean lineSizeError = true;
            if (lineSize.getValue() != null && !lineSize.getValue().isEmpty()) {
                try {
                    int lineWidth = Integer.parseInt(lineSize.getValue());
                    if (lineWidth > 0) {
                        CustomProperties.lineSize = lineWidth;
                        lineSizeError = false;
                    }
                } catch (Exception ignored) {
                }
            }
            if (lineSizeError) {
                exportModuleStatus.setText("Hrúbka čiary musí byť celé kladné číslo !");
                return;
            }
            if (!selectShowTrack.isSelected()) {
                CustomProperties.lineColor = "00ffffff";
                CustomProperties.lineColorHighlighted = "00ffffff";
            }
            if (!CustomProperties.pathToTarget.endsWith(".kml")) {
                CustomProperties.pathToTarget = CustomProperties.pathToTarget + "\\" + ((targetFileName.getText() == null || targetFileName.getText().equals("")) ? "output" : targetFileName.getText()) + ".kml";
            }
            ProcessOutput output = new ProcessOutput();
            KmlRouteData outputData = output.process(processInputResult.getConvertedData());
            output.writeFile(outputData, CustomProperties.pathToTarget);
        }
    }

    public void pickColor() {
        CustomProperties.lineColor = toHexString(lineColorPicker.getValue()).replace("#", "#bb");
        CustomProperties.lineColorHighlighted = toHexString(lineColorPicker.getValue()).replace("#", "#ff");
    }
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getBlue() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getRed() * 255));
    }

    public void toggleShowTrack() {
        trackSettingsPane.setVisible(!trackSettingsPane.isVisible());
    }
}
