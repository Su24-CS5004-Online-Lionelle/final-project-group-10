package student.controller;

import student.model.ICharacter;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;
import student.view.JFrameView;

import java.io.OutputStream;
import java.util.List;

// feel free to rename
public class CharacterController {

    private ICharacter characters;

    /** The format to output. */
    private Formats format = Formats.TXT;

    private JFrameView view;

//    public CharacterController(JFrameView view) {
//        this.view = view;
//    }

    public CharacterController(ICharacter character) {
        this.characters = character;
    }

    public void writeCharacters(List<ICharacter.CharacterRecord> characters, int format_choice, OutputStream output) {
        Formats format = Formats.values()[format_choice];
        DataFormatter.write(characters, format, output);
    }

    public void loadCharacters(String name, String status, String species, String gender, boolean ascending) {
        characters.loadCharacters(name, status, species, gender, ascending);
    }

    public List<ICharacter.CharacterRecord> getCharacterRecords() {
        return characters.getCharacterRecords();
    }
}
