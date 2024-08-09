import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    /** The Character model. */
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
        character.loadCharacters("Rick Sanchez", "Alive", "Human", "Male", false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();

        assertEquals(2, characterRecords.size());
    }

    /**
     * Tests the loading of character records with no filters.
     * Ensures that the loadCharacters method can handle null values for some filters correctly.
     */
    @Test
    public void testLoadCharactersWithNullFilters() {
        character.loadCharacters("Rick Sanchez", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();
        assertEquals(4, characterRecords.size());
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
            DataFormatter.write(characterRecords, format,
            new FileOutputStream(new File("C:\\Users\\56555\\Desktop\\data." + suffix)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the loading of characters with multiple pages of results.
     */
    @Test
    public void testLoadCharactersWithMultiplePages() {
        character.loadCharacters("Rick", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();
        assertEquals(107, characterRecords.size());
    }

    /**
     * Tests loading of characters with no results.
     */
    @Test
    public void testLoadCharactersInvalidSearch() {
        character.loadCharacters("Invalid Search", null, null, null, false);
        List<ICharacter.CharacterRecord> characterRecords = character.getCharacterRecords();
        assertEquals(0, characterRecords.size());
    }

    /** Test getURL method. */
    @Test
    void testGetURL() {
        character.loadURL("Rick", "Alive", "Human", "Male", true);
        assertNotNull(character.getURL(0));
        assertNull(character.getURL(-1));
    }

    /** Test loadURL method. */
    @Test
    void testLoadURL() {
        character.loadURL("Rick", "Alive", "Human", "Male", true);
        assertNotNull(character.getURL(0));
        assertNull(character.getURL(-1));
    }

    /** Test getCharByPage method. */
    @Test
    void testGetCharByPage() {
        character.loadURL("Rick", "Alive", "Human", "Male", true);
        List<ICharacter.CharacterRecord> characters = character.getCharByPage(true);
        assertNotNull(characters);
        assertFalse(characters.isEmpty());
        assertTrue(characters.get(0).name().contains("Rick"));
    }

    /** Test getter method getCurrIndex. */
    @Test
    void testGetCurrIndex() {
        assertEquals(0, character.getCurrIndex());
    }

    /** Test setter method setCurrIndex. */
    @Test
    void testSetCurrIndex() {
        character.setCurrIndex(5);
        assertEquals(5, character.getCurrIndex());
    }

    /** Test increasePages method. */
    @Test
    void testIncreasePages() {
        character.setCurrIndex(2);
        character.increasePages();
        assertEquals(3, character.getCurrIndex());
    }

    /** Test decreasePages method. */
    @Test
    void testDecreasePages() {
        character.setCurrIndex(2);
        character.decreasePages();
        assertEquals(1, character.getCurrIndex());
    }
}
