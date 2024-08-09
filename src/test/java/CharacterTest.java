import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.model.Character;
import student.model.ICharacter;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * This class tests the functionality of the Character class
 * ensuring that character data loading, sorting, 
 * and writing to file processes are functioning as expected.
 */
public class CharacterTest {

    private Character character = null;

    /**
     * Initializes a new instance of the Character class.
     */
    @BeforeEach
    void setUp() {
        character = new Character();
    }

    /**
     * Tests basic loading of character records from the API.
     * ensures that characters can be retrieved and stored correctly without any sorting.
     */
    @Test
    public void testLoadCharactersBasicInfo() {
        character.loadCharacters("111", "Alive", "Human", "Male", false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)
        System.out.println(1);
    }

    /**
     * Tests the loading of character records with a filter on episodes.
     * Ensures that the loadCharacters method can handle null values for some filters correctly.
     */
    @Test
    public void testLoadCharactersWithEpisodeFilter() {
//        character.loadCharacters("Rick Sanchez", null, null, null, null);
        character.loadCharacters("Rick Sanchez", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)

        System.out.println(1);
    }

    /**
     * Tests the functionality of writing character data to a file in TXT format.
     */
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

    /**
     * Tests the loading of characters with multiple pages of results.
     */
    @Test
    public void testLoadCharactersWithMultiplePages() {
        character.loadCharacters("Rick Sanchez", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        //view.updateTable(characterRecords)

        System.out.println(1);
    }
}
