package utils;


public class FileReaderManager {

    private static final FileReaderManager FILE_READER_MANAGER = new FileReaderManager();
    private static ConfigFileReader configFileReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return FILE_READER_MANAGER;
    }

    public ConfigFileReader getConfigFileReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }
}

