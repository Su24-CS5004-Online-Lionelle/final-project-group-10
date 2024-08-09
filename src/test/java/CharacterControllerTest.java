import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.controller.CharacterController;
import student.model.Character;
import student.model.ICharacter.CharacterRecord;
import student.view.JFrameView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class to test the methods in the CharacterController class.
 * Many getter and setter methods from the model layer that the controller accesses to minimize interaction
 * between the model and the view layers are tested in the model test classes.
 */
class CharacterControllerTest {

    /** A controller for testing. */
    private CharacterController controller;
    /** A model for testing. */
    private Character characterModel;

    /**
     * Sets up instantiation of model and controller for testing.
     */
    @BeforeEach
    void setUp() {
        characterModel = new Character();
        controller = new CharacterController(characterModel);
    }

    /**
     * Test that the view is set up. We can assume that the view is set correctly
     * if no exceptions are thrown.
     */
    @Test
    void testSetView() {
        JFrameView view = new JFrameView(controller);
        controller.setView(view);
    }

    /**
     * Test writing of characters out to JSON, CSV, and XML formats.
     */
    @Test
    void testWriteCharacters() {
        List<CharacterRecord> characters = new ArrayList<>();
        characters.add(new CharacterRecord(37, "Beth Sanchez", "Alive", 
        "Human", "Female", "https://rickandmortyapi.com/api/character/avatar/37.jpeg"));
        characters.add(new CharacterRecord(397, "Fido", "Alive", 
        "Animal", "Male", "https://rickandmortyapi.com/api/character/avatar/397.jpeg"));

        OutputStream out = new ByteArrayOutputStream();

        // Test writing characters in JSON format
        controller.writeCharacters(characters, "json", out);
        String outputJson = out.toString();
        assertTrue(outputJson.contains("\"name\" : \"Beth Sanchez\""));
        assertTrue(outputJson.contains("\"name\" : \"Fido\""));

        // Test writing characters in CSV format
        out = new ByteArrayOutputStream();
        controller.writeCharacters(characters, "csv", out);
        String outputCsv = out.toString();
        assertTrue(outputCsv.contains("Beth Sanchez,Alive,Human,Female"));
        assertTrue(outputCsv.contains("Fido,Alive,Animal,Male"));

        // Test writing characters in XML format
        out = new ByteArrayOutputStream();
        controller.writeCharacters(characters, "xml", out);
        String outputXml = out.toString();
        assertTrue(outputXml.contains("<name>Beth Sanchez</name>"));
        assertTrue(outputXml.contains("<name>Fido</name>"));
    }

    /**
     * Test loadCharacters method.
     */
    @Test
    void testLoadCharacters() {
        // Assuming the character model has some characters loaded for the test

        List<CharacterRecord> loadedCharacters = controller.loadCharacters("Rick", "Alive", "Human", "Male", true);
        assertNotNull(loadedCharacters);
        assertFalse(loadedCharacters.isEmpty());
        assertEquals("Baby Rick", loadedCharacters.get(0).name());
    }

    /**
     * Test getCharacterRecords method.
     */
    @Test
    void testGetCharacterRecords() {
        List<CharacterRecord> characterRecords = controller.getCharacterRecords();
        assertNotNull(characterRecords);
        // Assuming there are some character records in the model
        assertTrue(characterRecords.isEmpty());
    }

    /**
     * Test that the txtPrint method prints correctly.
     */
    @Test
    void testTxtPrint() {
        CharacterRecord record = new CharacterRecord(397, "Fido", "Alive",
         "Animal", "Male", "https://rickandmortyapi.com/api/character/avatar/397.jpeg");
        String result = controller.txtPrint(record);
        assertTrue(result.contains("Name: Fido"));
        assertTrue(result.contains("Status: Alive"));
        assertTrue(result.contains("Species: Animal"));
        assertTrue(result.contains("Gender: Male"));
    }

    /**
     * Test getter method for the model works correctly.
     */
    @Test
    void testGetModel() {
        Character model = controller.getModel();
        assertNotNull(model);
        assertSame(characterModel, model);
    }
}
