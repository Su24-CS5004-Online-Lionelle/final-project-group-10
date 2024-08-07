package student;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;

/**
 * A class that stores the settings for the application,
 * such as the names of buttons, menus, and font settings.
 * This class also sets the UI manager to the user's system look and feel.
 */
public final class Settings {
    /** An instance of the Settings class. */
    private static Settings instance = new Settings();
    /** Properties object to store the properties. */
    private final Properties prop;
    /** The caption of the frame. */
    private final String caption;
    /** The String properties for the search button. */
    private final String search;
    /** The String properties for the filter by gender label. */
    private final String gender;
    /** The String properties for the filter by status label. */
    private final String status;
    /** The String properties for the filter by species label. */
    private final String species;
    /** The String properties for the export results button. */
    private final String export;
    /** The String properties for the next button. */
    private final String next;
    /** The String properties for the previous button. */
    private final String previous;
    /** The String properties for the sort label. */
    private final String sort;
    /** The font to use. */
    private final String font;
    /** The font size to use. */
    private final int fontSize;
    /** The String properties of the filter by gender dropdown menu. */
    private final String genderOption;
    /** The String properties of the filter by status dropdown menu. */
    private final String statusOption;
    /** The String properties of the filter by species dropdown menu. */
    private final String speciesOption;
    /** The String properties of the sort by name ascending or descending dropdown menu. */
    private final String sortOption;

    /**
     * Constructor for the Settings class.
     */
    private Settings() {
        prop = new Properties();
        loadProperties();
        caption = prop.getProperty("caption");
        search = prop.getProperty("search");
        gender = prop.getProperty("gender");
        status = prop.getProperty("status");
        species = prop.getProperty("species");
        export = prop.getProperty("export");
        next = prop.getProperty("next");
        previous = prop.getProperty("previous");
        sort = prop.getProperty("sort");
        font = prop.getProperty("font");
        fontSize = Integer.parseInt(prop.getProperty("font_size"));
        genderOption = prop.getProperty("gender_option");
        statusOption = prop.getProperty("status_option");
        speciesOption = prop.getProperty("species_option");
        sortOption = prop.getProperty("sort_option");
        setUIManager();
    }

    /**
     * Getter method for the caption.
     * 
     * @return The caption of the JFrame.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Getter method for the String properties of the search button.
     * 
     * @return The string for the search button.
     */
    public String getSearch() {
        return search;
    }

    /**
     * Getter method for the String properties of the filter by gender JLabel. 
     * 
     * @return The string for the filter by gender JLabel.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Getter method for the String properties of the filter by status JLabel.
     * 
     * @return The string for the filter by status JLabel.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter method for the String properties of the filter by species JLabel.
     * 
     * @return The string for the filter by species JLabel.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Getter method for the String properties of the export button.
     * 
     * @return The string for the export button.
     */
    public String getExport() {
        return export;
    }

    /**
     * Getter method for the String properties of the next button.
     * 
     * @return The string for the next button.
     */
    public String getNext() {
        return next;
    }

    /**
     * Getter method for the String properties of the previous button.
     * 
     * @return The string for the previous button.
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * Getter method for the String properties of the Sort JLabel.
     * 
     * @return The string for the sort JLabel.
     */
    public String getSort() {
        return sort;
    }

    /**
     * Getter method for the font.
     * 
     * @return The font to use.
     */
    public String getFont() {
        return font;
    }

    /**
     * Getter method for the font size.
     * 
     * @return The font size to use.
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Getter method for the String properties of the options for the filter by gender dropdown menu.
     * 
     * @return The options for the filter by gender dropdown menu.
     */
    public String getGenderOption() {
        return genderOption;
    }

    /**
     * Getter method for the String properties of the options for the filter by status dropdown menu.
     * 
     * @return The options for the filter by status dropdown menu.
     */
    public String getStatusOption() {
        return statusOption;
    }

    /**
     * Getter method for the String properties of the options for the filter by species dropdown menu.
     * 
     * @return The options for the filter by species dropdown menu.
     */
    public String getSpeciesOption() {
        return speciesOption;
    }

    /**
     * Getter method for the String properties of the options for the sort by name dropdown menu.
     * 
     * @return The options for the sort by name dropdown menu.
     */
    public String getSortOption() {
        return sortOption;
    }

    /** Sets the UI manager to the user's system look and feel. */
    private static void setUIManager() {
        try {
            UIManager.setLookAndFeel((UIManager.getSystemLookAndFeelClassName()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** Loads the properties from the config XML file. */
    private void loadProperties() {
        try {
            InputStream is = Settings.class.getResourceAsStream("/config.xml");
            if (is == null) {
                throw new IOException("Cannot find the file");
            }
            prop.loadFromXML(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A getter method to retrieve an instance of the Settings class.
     * 
     * @return the instance of the Settings class.
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
