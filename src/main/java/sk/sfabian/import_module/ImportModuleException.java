package sk.sfabian.import_module;

public class ImportModuleException extends Exception{
    public enum ImportModuleExceptionType {
        UNSUPPORTED_FILE_FORMAT,
        KML_STRUCTURE_NOT_RECOGNIZED,
        OTHER
    }
    private ImportModuleExceptionType type;
    public ImportModuleException(ImportModuleExceptionType type, String message) {
        super(message);
        this.type = type;
    }
    public ImportModuleExceptionType getType() {
        return type;
    }
}
