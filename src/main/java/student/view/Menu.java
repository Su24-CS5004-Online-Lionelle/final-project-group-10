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
    private String selected_gender = "All";
    private String selected_status = "All";
    private String selected_species = "All";
    private String selected_sort = "All";

    public Menu() {
        String[] genders = SETTINGS.GENDER_OPTION.split(",");
        String[] statuses = SETTINGS.STATUS_OPTION.split(",");
        String[] species = SETTINGS.SPECIES_OPTION.split(",");
        String[] sorts = SETTINGS.SORT.split(",");

        gender_box = new JComboBox<>(genders);
        status_box = new JComboBox<>(statuses);
        species_box = new JComboBox<>(species);
        sort_box = new JComboBox<>(sorts);

        gender_box.addItemListener(this);
        status_box.addItemListener(this);
        species_box.addItemListener(this);
        sort_box.addItemListener(this);

        add(new JLabel(SETTINGS.GENDER));
        add(gender_box);
        add(new JLabel(SETTINGS.STATUS));
        add(status_box);
        add(new JLabel(SETTINGS.SPECIES));
        add(species_box);
        add(new JLabel(SETTINGS.SORT));
        add(sort_box);


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
        if (e.getSource() == gender_box) {
            selected_gender = gender_box.getSelectedItem().toString();
        }
        if (e.getSource() == status_box) {
            selected_status = status_box.getSelectedItem().toString();
        }
        if (e.getSource() == species_box) {
            selected_species = species_box.getSelectedItem().toString();
        }
        if (e.getSource() == sort_box) {
            selected_sort = sort_box.getSelectedItem().toString();
        }
    }

    public String GETGENDER() {
        return selected_gender;
    }

    public String GETSTATUS() {
        return selected_status;
    }

    public String GETSPECIES() {
        return selected_species;
    }

    public String GETSORT() {
        return selected_sort;
    }
}
