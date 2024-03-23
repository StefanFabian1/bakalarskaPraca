package sk.sfabian.import_module;

import sk.sfabian.export_module.model.source.ConvertedData;

import java.io.File;

public abstract class ProcessInput {
    public abstract ProcessInputResult convertInput(File file) throws ImportModuleException;
}
