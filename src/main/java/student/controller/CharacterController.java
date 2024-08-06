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
    /** The character model. */
    private final ICharacter character;
    /** The format to output. */
    private Formats format = Formats.TXT;
    /** JFrameView to display output to the user. */
    private JFrameView view;

    public void setView(JFrameView view) {
        this.view = view;
    }

    public CharacterController(ICharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("Character model is not initialized");
        }
        this.character = character;
//        this.view = JFrameView.getInstance(this);
    }

    public void writeCharacters(List<ICharacter.CharacterRecord> characters, int format_choice, OutputStream output) {
        Formats format = Formats.values()[format_choice];
        DataFormatter.write(characters, format, output);
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

    public Character getModel() {
        return (Character) character;
    }
}
