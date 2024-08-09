import student.model.formatters.DataFormatter;
import student.model.ICharacter.CharacterRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for DataFormatter class.
 * Contains tests that verify the functionality of formatting data into 
 * different formats, such as text, XML, and CSV 
 * based on a list of CharacterRecords.
 * 
 * This class inherently tests the DomainXMLWrapper class.
 */
public class DataFormatterTest {

    /** List of CharacterRecords to use for testing. */
    private List<CharacterRecord> characters;

    /**
     * Sets up the test environment before each test method
     * Initializes a list of CharacterRecords with sample data to be used in the tests.
     */
    @BeforeEach
    public void setUp() {
        characters = new ArrayList<>();
        characters.add(new CharacterRecord(1, "Rick Sanchez", "Alive", "Human", "Male", "imageURL"));
        characters.add(new CharacterRecord(2, "Morty Smith", "Alive", "Human", "Male", "imageURL"));
    }

    /**
     * Tests the txtPrint method.
     */
    @Test
    public void testTxtPrint() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.txtPrint(characters, out);
        String output = out.toString();
        assertTrue(output.contains("Name: Rick Sanchez"));
        assertTrue(output.contains("Status: Alive"));
        assertTrue(output.contains("Species: Human"));
        assertTrue(output.contains("Gender: Male"));
    }

    /**
     * Tests the writeXmlData method.
     * Inherently tests DomainXMLWrapper.
     */
    @Test
    public void testWriteXmlData() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.writeXmlData(characters, out);
        String output = out.toString();
        assertTrue(output.contains("<characterList>"));
        assertTrue(output.contains("<name>Rick Sanchez</name>"));
        assertTrue(output.contains("<status>Alive</status>"));
        assertTrue(output.contains("<species>Human</species>"));
        assertTrue(output.contains("<gender>Male</gender>"));
    }

    /**
     * Tests the writeCSVData method.
     */
    @Test
    public void testWriteCSVData() {
        OutputStream out = new ByteArrayOutputStream();
        DataFormatter.writeCSVData(characters, out);
        String output = out.toString();
        assertTrue(output.startsWith("name,status,species,gender,imageurl"));
        assertTrue(output.contains("Rick Sanchez,Alive,Human,Male,imageURL"));
        assertTrue(output.contains("Morty Smith,Alive,Human,Male,imageURL"));
    }
}
