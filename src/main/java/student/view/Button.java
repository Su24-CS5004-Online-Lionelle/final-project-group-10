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
    private final JTextField searchField;
    /**
     * Dropdown for gender.
     */
    private final JComboBox<String> genderBox;
    private final JComboBox<String> statusBox;
    private final JComboBox<String> speciesBox;
    private final JComboBox<String> sortBox;



    /**
     * @param buttonType
     * @param controller
     * @param searchField
     * @param genderBox
     * @param statusBox
     * @param speciesBox
     * @param sortBox
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


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (bt) {
                case SEARCH:
                    loadCharacters();
                    displayChar();
                    break;

                case EXPORT:
                    String name1 = searchField.getText();
                    String gender1 = (String) genderBox.getSelectedItem();
                    String status1 = (String) statusBox.getSelectedItem();
                    String species1 = (String) speciesBox.getSelectedItem();
                    String sort1 = (String) sortBox.getSelectedItem();

                    if ("all".equalsIgnoreCase(gender1)) {
                        gender1 = "";
                    }
                    if ("all".equalsIgnoreCase(status1)) {
                        status1 = "";
                    }
                    if ("all".equalsIgnoreCase(species1)) {
                        species1 = "";
                    }

                    boolean ascending1 = "ascending".equalsIgnoreCase(sort1);
                    List<ICharacter.CharacterRecord> characters1 = 
                            controller.loadCharacters(name1, status1, species1, gender1, ascending1);
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
                    controller.increasePade();
                    displayChar();
                    break;

                case PREVIOUS:
                    controller.decreasePade();
                    displayChar();
                    break;

                default:
                    break; // should not reach here.
            }
        }
    }

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
