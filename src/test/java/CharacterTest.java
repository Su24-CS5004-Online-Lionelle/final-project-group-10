import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.model.Character;
import student.model.ICharacter;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

public class CharacterTest {

    private Character character = null;

    @BeforeEach
    void setUp() {
        character = new Character();
    }

    @Test
    public void testLoadCharactersBasicInfo() {
        character.loadCharacters("111", "Alive", "Human", "Male", false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)
        System.out.println(1);
    }

    @Test
    public void testLoadCharactersWithEpisodeFilter() {
//        character.loadCharacters("Rick Sanchez", null, null, null, null);
        character.loadCharacters("Rick Sanchez", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)

        System.out.println(1);
    }

    @Test
    public void testWriteCharacterDataToFile() {
        try {
            character.loadCharacters("Rick Sanchez", null, null, null, false);
            List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

            Formats format = Formats.TXT;
            String suffix = format.name();

            //VIEW  need to select a path for the file
            //new File("C:\\Users\\56555\\Desktop\\data." + suffix)
            DataFormatter.write(characterRecords, format, new FileOutputStream(new File("C:\\Users\\56555\\Desktop\\data." + suffix)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadCharactersWithMultiplePages() {
        character.loadCharacters("Rick Sanchez", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)

        System.out.println(1);
    }
}
