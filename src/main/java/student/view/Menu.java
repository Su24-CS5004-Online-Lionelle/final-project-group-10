package student.view;

import student.Settings;

import javax.swing.*;
import java.awt.event.*;


public class Menu extends JFrame implements ItemListener {
    private static final Settings SETTINGS = Settings.getInstance();
    static JComboBox<String> gender_box;
    static JComboBox<String> status_box;
    static JComboBox<String> species_box;
    static JComboBox<String> sort_box;


    public Menu() {
        String[] genders = SETTINGS.GENDER_OPTION.split(",");
        String[] statuses = SETTINGS.STATUS_OPTION.split(",");
        String[] species = SETTINGS.SPECIES_OPTION.split(",");
        String[] sorts = SETTINGS.SORT_OPTION.split(",");

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

    public JComboBox<String> getGenderBox() {
        return gender_box;
    }

    public JComboBox<String> getStatusBox() {
        return status_box;
    }

    public JComboBox<String> getSpeciesBox() {
        return species_box;
    }

    public JComboBox<String> getSortBox() {
        return sort_box;
    }
    
}
