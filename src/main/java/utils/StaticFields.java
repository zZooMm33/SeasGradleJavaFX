package utils;

public class StaticFields {

    private static final String PROP_FILE_NAME = "config.properties";

    private static final String NAME_PROGRAM = "SeasGradleJavaFX";

    private static final String NAME_MAIN_WINDOW = "Seas";

    private static final String NAME_ABOUT_WINDOW = "About";

    private static final String NAME_SETTINGS_WINDOW = "Settings";

    public static String getPropFileName() {
        return PROP_FILE_NAME;
    }

    public static String getNameProgram() {
        return NAME_PROGRAM;
    }

    public static String getNameMainWindow() {
        return NAME_MAIN_WINDOW;
    }

    public static String getNameAboutWindow() {
        return NAME_ABOUT_WINDOW;
    }

    public static String getNameSettingsWindow() {
        return NAME_SETTINGS_WINDOW;
    }
}
