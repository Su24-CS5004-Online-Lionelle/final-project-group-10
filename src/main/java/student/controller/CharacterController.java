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

// feel free to rename
public class CharacterController {
    /**
     * The character model.
     */
    private final ICharacter character;
    /**
     * The format to output.
     */
    private Formats format = Formats.TXT;
    /**
     * JFrameView to display output to the user.
     */
    private JFrameView view;

    public void setView(JFrameView view) {
        this.view = view;
    }

    public CharacterController(ICharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("Character model is not initialized");
        }
        this.character = character;
    }


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

    public void loadURL(String name, String status, String species, String gender, boolean ascending) {
        try {
            character.loadURL(name, status, species, gender, ascending);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ICharacter.CharacterRecord> loadCharacters(String name, String status, String species, String gender, boolean ascending) {
        return character.loadCharacters(name, status, species, gender, ascending);
    }

    public List<ICharacter.CharacterRecord> getCharacterRecords() {
        return character.getCharacterRecords();
    }

    public String txtPrint(ICharacter.CharacterRecord character) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        DataFormatter.txtPrintSingle(character, ps);
        return baos.toString();
    }

    public List<ICharacter.CharacterRecord> loadCurrPage(int page, boolean ascending) {
        return character.getCharByPage(page, ascending);

    }

    public Character getModel() {
        return (Character) character;
    }
}
