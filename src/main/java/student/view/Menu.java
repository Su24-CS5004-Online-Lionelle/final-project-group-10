package student.view;

import student.Settings;

import javax.swing.*;
import java.awt.event.*;


/**
 * This class represents the main menu interface for the application,
 * allowing users to select various options such as gender, status, species, and sort order.
 * This class is responsible for setting up the UI components and handling item selection events.
 */
public class Menu extends JFrame implements ItemListener {
    /**An instance of the application settings.*/
    private static final Settings SETTINGS = Settings.getInstance();
    /**ComboBox for selecting gender options.*/
    private static JComboBox<String> genderBox;
     /**ComboBox for selecting status options.*/
    private static JComboBox<String> statusBOX;
    /**ComboBox for selecting species options.*/
    private static JComboBox<String> speciesBOX;
    /**ComboBox for selecting sort options.*/
    private static JComboBox<String> sortBOX;

    /**
     * Constructs a new `Menu` object, initializing the UI components based on
     * the settings retrieved from the `Settings` singleton.
     */
    public Menu() {
        String[] genders = SETTINGS.getGenderOption().split(",");
        String[] statuses = SETTINGS.getStatusOption().split(",");
        String[] species = SETTINGS.getSpeciesOption().split(",");
        String[] sorts = SETTINGS.getSortOption().split(",");

        genderBox = new JComboBox<>(genders);
        statusBOX = new JComboBox<>(statuses);
        speciesBOX = new JComboBox<>(species);
        sortBOX = new JComboBox<>(sorts);

        genderBox.addItemListener(this);
        statusBOX.addItemListener(this);
        speciesBOX.addItemListener(this);
        sortBOX.addItemListener(this);
    }


    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    /**
     * Returns the gender selection combo box.
     *
     * @return the combo box for gender selection
     */
    public JComboBox<String> getGenderBox() {
        return genderBox;
    }

    /**
     * Returns the status selection combo box.
     *
     * @return the combo box for status selection
     */
    public JComboBox<String> getStatusBox() {
        return statusBOX;
    }

    /**
     * Returns the species selection combo box.
     *
     * @return the combo box for species selection
     */
    public JComboBox<String> getSpeciesBox() {
        return speciesBOX;
    }

    /**
     * Returns the sort selection combo box.
     *
     * @return the combo box for sort selection
     */
    public JComboBox<String> getSortBox() {
        return sortBOX;
    }
    
}
