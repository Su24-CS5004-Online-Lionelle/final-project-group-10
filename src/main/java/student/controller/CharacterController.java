package student.controller;

import student.model.Character;
import student.model.ICharacter;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;
import student.view.JFrameView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class CharacterController {
    /**
     * The character model.
     */
    private final ICharacter character;
    /**
     * JFrameView to display output to the user.
     */
    private JFrameView view;

    /**
     * Set the view for the controller.
     * @param view the JFrameView to set.
     */
    public void setView(JFrameView view) {
        this.view = view;
    }

    /**
     * Constructor for the CharacterController.
     * 
     * @param character the character model.
     */
    public CharacterController(ICharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("Character model is not initialized");
        }
        this.character = character;
    }

    /**
     * Write the characters to the output stream in the specified format.
     *
     * @param characters the list of characters to write.
     * @param fileExtension the file extension.
     * @param out the output stream to write to.
     * @see DataFormatter#write(@Nonnull Collection<ICharacter.CharacterRecord> characters,
     *                                     @Nonnull Formats format, OutputStream out)
     */    
    public void writeCharacters(List<ICharacter.CharacterRecord> characters, String fileExtension, OutputStream out) {
        Formats format;
        switch (fileExtension) {
            case "json":
                format = Formats.JSON;
                break;
            case "csv":
                format = Formats.CSV;
                break;
            case "xml":
            default:
                format = Formats.XML;
                break;
        }
        DataFormatter.write(characters, format, out);
    }

    /**
     * Loads character data from a URL based on specified filters.
     *
     * @param name      the name of the character to filter by
     * @param status    the status of the character (e.g., alive, dead, unknown)
     * @param species   the species of the character
     * @param gender    the gender of the character
     * @param ascending whether the list should be sorted in ascending order
     */
    public void loadURL(String name, String status, String species, String gender, boolean ascending) {
        try {
            character.loadURL(name, status, species, gender, ascending);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the URL of a character based on their page index.
     *
     * @param num the page of the character
     * @return the URL as a string
     */
    public String getURL(int num) {
        return character.getURL(num);
    }

    /**
     * Loads a list of characters based on specified filters.
     *
     * @param name      the name of the character to filter by
     * @param status    the status of the character (e.g., alive, dead)
     * @param species   the species of the character
     * @param gender    the gender of the character
     * @param ascending whether the list should be sorted in ascending order
     * @return a list of character records matching the filters
     */
    public List<ICharacter.CharacterRecord> loadCharacters(String name, String status, String species, String gender,
                                                           boolean ascending) {
        character.loadURL(name, status, species, gender, ascending);
        return character.loadCharacters(name, status, species, gender, ascending);
    }

    /**
     * Retrieves the current list of character records.
     *
     * @return a list of current character records
     */
    public List<ICharacter.CharacterRecord> getCharacterRecords() {
        return character.getCharacterRecords();
    }

    /**
     * Formats a single character record into a text representation.
     *
     * @param character the character record to be formatted
     * @return the formatted character as a string
     */
    public String txtPrint(ICharacter.CharacterRecord character) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        DataFormatter.txtPrintSingle(character, ps);
        return baos.toString();
    }

    /**
     * Loads the characters on the current page.
     *
     * @param ascending whether the list should be sorted in ascending order
     * @return a list of character records on the current page
     */
    public List<ICharacter.CharacterRecord> loadCurrPage(boolean ascending) {
        return character.getCharByPage(ascending);

    }

    /**
     * Get the model component (which is the character class).
     * @return the character instance
     */
    public Character getModel() {
        return (Character) character;
    }

    /**
     * Retrieves the current page index.
     *
     * @return the current page index
     */
    public int getCurrentPage() {
        return character.getCurrIndex();
    }

    /**
     * Increases the current page index, moving to the next page.
     */
    public void increasePage() {
        character.increasePages();
    }

    /**
     * Decreases the current page index, moving to the previous page.
     */
    public void decreasePage() {
        character.decreasePages();
    }

    /**
     * Sets the current page index the character model.
     *
     * @param page the page index to be set
     */
    public void setPage(int page) {
        character.setCurrIndex(page);
    }
}
