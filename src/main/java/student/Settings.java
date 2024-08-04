package student;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;

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
        CAPTION = prop.getProperty("caption");
        SEARCH = prop.getProperty("search");
        GENDER = prop.getProperty("gender");
        STATUS = prop.getProperty("status");
        SPECIES = prop.getProperty("species");
        EXPORT = prop.getProperty("export");
        SORT = prop.getProperty("sort");
        FONT = prop.getProperty("font");
        FONT_SIZE = Integer.parseInt(prop.getProperty("font_size"));
        GENDER_OPTION = prop.getProperty("gender_option");
        STATUS_OPTION = prop.getProperty("status_option");
        SPECIES_OPTION = prop.getProperty("species_option");
        SORT_OPTION = prop.getProperty("sort_option");
        setUIManager();
    }

    private static void setUIManager(){
        try {
            UIManager.setLookAndFeel((UIManager.getSystemLookAndFeelClassName()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Properties loadProperties(){
        Properties prop = new Properties();
        try {
            InputStream is = Settings.class.getResourceAsStream("resources/config.xml");
            prop.loadFromXML(is);
            is.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public static Settings getInstance(){
        if (instance == null){
            instance = new Settings();
        }
        return instance;
    }
}
