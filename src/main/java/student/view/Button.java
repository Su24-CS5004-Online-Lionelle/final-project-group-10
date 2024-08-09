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
        /** Search button. */
        SEARCH,
        /** Export button. */
        EXPORT,
        /** Next button. */
        NEXT,
        /** Previous button. */
        PREVIOUS
    }

    /** Controller for the program. */
    private final CharacterController controller;
    /** Type of button. */
    private final ButtonType bt;
    /** Text field for search. */
    private final JTextField searchField;
    /** Dropdown menu to filter by gender. */
    private final JComboBox<String> genderBox;
    /** Dropdown menu to filter by status. */
    private final JComboBox<String> statusBox;
    /** Dropdown menu to filter by species. */
    private final JComboBox<String> speciesBox;
    /** Dropdown menu to sort results by name. */
    private final JComboBox<String> sortBox;

    /**
     * Constructor for the Buttons in the JFrame. 
     * 
     * @param buttonType button types include search, export, previous, and next buttons.
     * @param controller the controller for the program.
     * @param searchField the text field to search by name.
     * @param genderBox the dropdown menu to flter by gender.
     * @param statusBox the dropdown menu to filter by status.
     * @param speciesBox the dropdown menu to filter by species.
     * @param sortBox the dropdown menu to sort results by name. 
     */
    public Button(ButtonType buttonType, CharacterController controller, JTextField searchField,
                                    JComboBox<String> genderBox, JComboBox<String> statusBox,
                                    JComboBox<String> speciesBox, JComboBox<String> sortBox) {
        this.bt = buttonType;
        this.controller = controller;
        this.searchField = searchField;
        this.genderBox = genderBox;
        this.statusBox = statusBox;
        this.speciesBox = speciesBox;
        this.sortBox = sortBox;
        addActionListener(new ButtonListener());
    }

    /**
     * Creates the action listeners for each button type,
     * search, export, next, and previous.
     * The search button will load the characters and display them.
     * The export button will save the characters to a file.
     * The next button will display the next page of characters, if there is one.
     * The previous button will display the previous page of characters, if there is one.
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (bt) {
                case SEARCH:
                    loadCharacters();
                    controller.setPage(0);
                    displayChar();
                    break;

                case EXPORT:
                    String name = searchField.getText();
                    String gender = (String) genderBox.getSelectedItem();
                    String status = (String) statusBox.getSelectedItem();
                    String species = (String) speciesBox.getSelectedItem();
                    String sort = (String) sortBox.getSelectedItem();

                    if ("all".equalsIgnoreCase(gender)) {
                        gender = "";
                    }
                    if ("all".equalsIgnoreCase(status)) {
                        status = "";
                    }
                    if ("all".equalsIgnoreCase(species)) {
                        species = "";
                    }

                    boolean ascending = "ascending".equalsIgnoreCase(sort);

                    List<ICharacter.CharacterRecord> characters = 
                            controller.loadCharacters(name, status, species, gender, ascending);
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
                    controller.increasePage();
                    displayChar();
                    break;

                case PREVIOUS:
                    controller.decreasePage();
                    displayChar();
                    break;

                default:
                    break; // should not reach here.
            }
        }
    }

    /**
     * Loads the characters based on the search criteria.
     * If the option selected for gender, status, and/or species is "all",
     * then no filter is applied to these fields.
     */
    private void loadCharacters() {
        String name = searchField.getText();
        String gender = (String) genderBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();
        String species = (String) speciesBox.getSelectedItem();
        String sort = (String) sortBox.getSelectedItem();

        if ("all".equalsIgnoreCase(gender)) {
            gender = "";
        }
        if ("all".equalsIgnoreCase(status)) {
            status = "";
        }
        if ("all".equalsIgnoreCase(species)) {
            species = "";
        }

        boolean ascending = "ascending".equalsIgnoreCase(sort); // true if ascending, false if descending

        controller.loadURL(name, status, species, gender, ascending);
    }

    /** 
     * Displays the characters based on the search criteria.
     * If there is a next page, the next button in the JFrame will be enabled.
     * If there is a previous page, the previous button in the JFrame will be enabled.
     */
    private void displayChar() {
        String sort = (String) sortBox.getSelectedItem();
        boolean ascending = "ascending".equalsIgnoreCase(sort);
        List<ICharacter.CharacterRecord> characters = controller.loadCurrPage(ascending);
        JFrameView.getInstance(controller).displayResults(characters);

        // if there's a next page
        boolean hasNext = !(controller.getURL(controller.getCurrentPage() + 1) == null);
        JFrameView.getInstance(controller).toggleNextButton(hasNext);
        // if there's a prev page
        boolean hasPrev = controller.getCurrentPage() > 0 && !controller.loadCurrPage(ascending).isEmpty();
        JFrameView.getInstance(controller).togglePrevButton(hasPrev);
    }
}
