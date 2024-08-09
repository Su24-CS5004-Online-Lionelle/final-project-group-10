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
    static JComboBox<String> gender_box;
     /**ComboBox for selecting status options.*/
    static JComboBox<String> status_box;
    /**ComboBox for selecting species options.*/
    static JComboBox<String> species_box;
    /**ComboBox for selecting sort options.*/
    static JComboBox<String> sort_box;

    /**
     * Constructs a new `Menu` object, initializing the UI components based on
     * the settings retrieved from the `Settings` singleton.
     */
    public Menu() {
        String[] genders = SETTINGS.getGenderOption().split(",");
        String[] statuses = SETTINGS.getStatusOption().split(",");
        String[] species = SETTINGS.getSpeciesOption().split(",");
        String[] sorts = SETTINGS.getSortOption().split(",");

        gender_box = new JComboBox<>(genders);
        status_box = new JComboBox<>(statuses);
        species_box = new JComboBox<>(species);
        sort_box = new JComboBox<>(sorts);

        gender_box.addItemListener(this);
        status_box.addItemListener(this);
        species_box.addItemListener(this);
        sort_box.addItemListener(this);
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
        return gender_box;
    }

    /**
     * Returns the status selection combo box.
     *
     * @return the combo box for status selection
     */
    public JComboBox<String> getStatusBox() {
        return status_box;
    }

    /**
     * Returns the species selection combo box.
     *
     * @return the combo box for species selection
     */
    public JComboBox<String> getSpeciesBox() {
        return species_box;
    }

    /**
     * Returns the sort selection combo box.
     *
     * @return the combo box for sort selection
     */
    public JComboBox<String> getSortBox() {
        return sort_box;
    }
    
}
