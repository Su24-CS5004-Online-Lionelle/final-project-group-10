import student.model.formatters.DataFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.model.Character;
import student.model.ICharacter.CharacterRecord;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Test class for the DataFormatter test.
 * Also inherently tests the DomainXmlWrapper class, see testWriteXmlData. 
 */
public class DataFormatterTest {

    /** List of CharacterRecord records to use for testing. */
    private List<CharacterRecord> mockCharacters;

    /**
     * Set up the test environment by loading the mockCharacters list with one character search.
     */
    @BeforeEach
    public void setUp() {
        Character character = new Character();
        character.loadCharacters("Rick", "Alive", "Human", "Male", true);
        mockCharacters = character.getCharacterRecords();
    }

    /**
     * Inherently tests txtPrintSingle.
     */
    @Test
    public void testTxtPrint() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.txtPrint(mockCharacters, out);
        assertTrue(out.toString().contains("Name: Rick Sanchez"));
        assertTrue(out.toString().contains("Status: Alive"));
        assertTrue(out.toString().contains("Species: Human"));
        assertTrue(out.toString().contains("Gender: Male"));
    }

    /**
     * Inherently tests DomainXmlWrapper.
     */
    @Test
    public void testWriteXmlData() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.writeXmlData(mockCharacters, out);
        assertTrue(out.toString().contains("<domain>"));
        assertTrue(out.toString().contains("<name>Rick Sanchez</name>"));
        assertTrue(out.toString().contains("<status>Alive</status>"));
        assertTrue(out.toString().contains("<species>Human</species>"));
        assertTrue(out.toString().contains("<gender>Male</gender>"));
        assertTrue(out.toString().contains("<image>https://rickandmortyapi.com/api/character/avatar/1.jpeg</image>"));
        assertTrue(out.toString().contains("</domain>"));
    }

    @Test
    public void testWriteJsonData() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.writeJsonData(mockCharacters, out);
        assertTrue(out.toString().contains("\"name\" : \"Rick Sanchez\""));
        assertTrue(out.toString().contains("\"status\" : \"Alive\""));
        assertTrue(out.toString().contains("\"species\" : \"Human\""));
        assertTrue(out.toString().contains("\"gender\" : \"Male\""));
        assertTrue(out.toString().contains("\"image\" : \"https://rickandmortyapi.com/api/character/avatar/1.jpeg\""));
    }

    @Test
    public void testWriteCSVData() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.writeCSVData(mockCharacters, out);
        assertTrue(out.toString().contains("Rick Sanchez,Alive,Human,Male,https://rickandmortyapi.com/api/character/avatar/1.jpeg"));
    }
}
