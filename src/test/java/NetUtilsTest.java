
import student.model.util.NetUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NetUtilsTest {

    @Test
    public void testGetCharacterData() {
        try {
            String name = "Rick Sanchez";
            String status = "Alive";
            String species = "Human";
            String gender = "Male";
            List<String> episodes = Arrays.asList("1");
    
            String result = NetUtils.getCharacterData(name, status, species, gender, episodes);
            assertNotNull(result);
            assertTrue(result.contains("Rick Sanchez"));
            assertTrue(result.contains("\"status\":\"Alive\""));
            assertTrue(result.contains("\"species\":\"Human\""));
            assertTrue(result.contains("\"gender\":\"Male\""));
    
            System.out.println("API Response: " + result);
        } catch (IOException | InterruptedException e) {
            fail("Exception during API call: " + e.getMessage());
        }
    }

    @Test
    public void testGetCharacterDataWithNullName() {
        try {
            String result = NetUtils.getCharacterData(null, "Alive", "Human", "Male", Arrays.asList("1"));
            assertNotNull(result);
            assertFalse(result.contains("error"));
        } catch (Exception e) {
            fail("Should handle null name gracefully.");
        }
    }

    @Test
    public void testGetCharacterDataWithInvalidName() {
        try {
            String result = NetUtils.getCharacterData("InvalidName", "Alive", "Human", "Male", Arrays.asList("1"));
            assertTrue(result.contains("error"));
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown for invalid names.");
        }
    }

    @Test
    public void testGetCharacterDataPerformance() {
        long startTime = System.currentTimeMillis();
        try {
            NetUtils.getCharacterData("Rick", "Alive", "Human", "Male", Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        } catch (Exception e) {
            fail("Exception should not be thrown during performance test.");
        }
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 1000);
    }
}
