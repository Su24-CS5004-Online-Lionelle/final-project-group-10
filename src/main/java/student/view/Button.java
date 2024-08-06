package student.view;

import student.controller.CharacterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/** Button class for the view of the program. */
public class Button extends JButton {

    /** Enum for the type of button. */
    enum ButtonType {
        /** Search button. */
        SEARCH,
        /** Export button. */
        EXPORT
    }

    /** Controller for the program. */
    private final CharacterController controller;
    /** Type of button. */
    private final ButtonType bt;
    /** Text field for search. */
    private final JTextField search_field;
    /** Dropdown for gender. */
    private final String gender_field;
    private final String status_field;
    private final String species_field;
    private final String sort_field;

    public Button(ButtonType buttonType, CharacterController controller, JTextField search_field,
                  String gender_field, String status_field, String species_field, String sort_field) {
        this.bt = buttonType;
        this.controller = controller;
        this.search_field = search_field;
        this.gender_field = gender_field;
        this.status_field = status_field;
        this.species_field = species_field;
        this.sort_field = sort_field;
        addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (bt) {
                case SEARCH:
                    String name = search_field.getText();
                    String gender = gender_field;
                    String status = status_field;
                    String species = species_field;
                    String sort = sort_field;

                    if ("all".equalsIgnoreCase(gender)) gender = "";
                    if ("all".equalsIgnoreCase(status)) status = "";
                    if ("all".equalsIgnoreCase(species)) species = "";

                    boolean ascending = "ascending".equalsIgnoreCase(sort); // true if ascending, false if descending

                    controller.loadCharacters(name, status, species, gender, ascending);
                    break;

                case EXPORT:
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save File");

                    // Add dropdown for file format
                    JComboBox<String> formatDropdown = new JComboBox<>(new String[]{"XML", "JSON", "CSV"});
                    JPanel accessoryPanel = new JPanel();
                    accessoryPanel.add(new JLabel("Format:"));
                    accessoryPanel.add(formatDropdown);
                    fileChooser.setAccessory(accessoryPanel);

                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        String selectedFormat = formatDropdown.getSelectedItem().toString();
                        int formatChoice = 0; // Default to XML

                        switch (selectedFormat) {
                            case "JSON":
                                formatChoice = 1;
                                break;
                            case "CSV":
                                formatChoice = 2;
                                break;
                        }

                        try (OutputStream out = new FileOutputStream(fileToSave)) {
                            controller.writeCharacters(controller.getCharacterRecords(), formatChoice, out);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
}
