package sk.sfabian.import_module;

import sk.sfabian.export_module.model.source.ConvertedData;

import java.io.File;
import java.util.List;

public abstract class ProcessInput {
    public abstract List<ConvertedData> convertInput(File file);
}
