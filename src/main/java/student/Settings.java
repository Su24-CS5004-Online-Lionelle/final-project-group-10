package student;

import java.util.Properties;

public class Settings {
    private static Settings instance;
    public final String CAPTION;
    public final String SEARCH;
    public final String GENDER;
    public final String STATUS;
    public final String SPECIES;
    public final String EXPORT;
    public final String SORT;
    public final String FONT;
    public final int FONT_SIZE;
    public final String GENDER_OPTION;
    public final String STATUS_OPTION;
    public final String SPECIES_OPTION;
    public final String SORT_OPTION;

    private Settings(){
        Properties prop = loadProperties();
    }
}
