package sk.sfabian.import_module;

import sk.sfabian.export_module.model.source.ConvertedData;

import java.util.List;

public class ProcessInputResult {
    private String message;
    private List<ConvertedData> convertedData;

    public ProcessInputResult(String message, List<ConvertedData> convertedData) {
        this.message = message;
        this.convertedData = convertedData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ConvertedData> getConvertedData() {
        return convertedData;
    }

    public void setConvertedData(List<ConvertedData> convertedData) {
        this.convertedData = convertedData;
    }
}
