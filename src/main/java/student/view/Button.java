package student.view;

import student.controller.CharacterController;
import student.model.ICharacter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Button class for the view of the program.
 */
public class Button extends JButton {

    /**
     * Enum for the type of button.
     */
    enum ButtonType {
        /**
         * Search button.
         */
        SEARCH,
        /**
         * Export button.
         */
        EXPORT,
        /**
         * Next button.
         */
        NEXT,
        /**
         * Previous button.
         */
        PREVIOUS
    }

    /**
     * Controller for the program.
     */
    private final CharacterController controller;
    /**
     * Type of button.
     */
    private final ButtonType bt;
    /**
     * Text field for search.
     */
    private final JTextField search_field;
    /**
     * Dropdown for gender.
     */
    private final JComboBox<String> gender_box;
    private final JComboBox<String> status_box;
    private final JComboBox<String> species_box;
    private final JComboBox<String> sort_box;

    public Button(ButtonType buttonType, CharacterController controller, JTextField search_field,
                  JComboBox<String> gender_box, JComboBox<String> status_box, JComboBox<String> species_box, JComboBox<String> sort_box) {
        this.bt = buttonType;
        this.controller = controller;
        this.search_field = search_field;
        this.gender_box = gender_box;
        this.status_box = status_box;
        this.species_box = species_box;
        this.sort_box = sort_box;
        addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (bt) {
                case SEARCH:
                    String name = search_field.getText();
                    String gender = (String) gender_box.getSelectedItem();
                    String status = (String) status_box.getSelectedItem();
                    String species = (String) species_box.getSelectedItem();
                    String sort = (String) sort_box.getSelectedItem();

                    if ("all".equalsIgnoreCase(gender)) gender = "";
                    if ("all".equalsIgnoreCase(status)) status = "";
                    if ("all".equalsIgnoreCase(species)) species = "";

                    boolean ascending = "ascending".equalsIgnoreCase(sort); // true if ascending, false if descending

                    List<ICharacter.CharacterRecord> characters = controller.loadCharacters(name, status, species, gender, ascending);
                    JFrameView.getInstance(controller).displayResults(characters);
                    break;

                case EXPORT:
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save File");

                    FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("*.xml", "xml");
                    FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("*.json", "json");
                    FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("*.csv", "csv");

                    fileChooser.addChoosableFileFilter(xmlFilter);
                    fileChooser.addChoosableFileFilter(jsonFilter);
                    fileChooser.addChoosableFileFilter(csvFilter);
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        String extension = "";

                        if (fileChooser.getFileFilter() == jsonFilter) {
                            extension = "json";
                        } else if (fileChooser.getFileFilter() == csvFilter) {
                            extension = "csv";
                        } else {
                            extension = "xml";
                        }

                        if (!fileToSave.getName().endsWith("." + extension)) {
                            fileToSave = new File(fileToSave.getAbsolutePath() + "." + extension);
                        }

                        try (OutputStream out = new FileOutputStream(fileToSave)) {
                            controller.writeCharacters(controller.getCharacterRecords(), extension, out);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                case NEXT:

                case PREVIOUS:
            }
        }
    }
}
