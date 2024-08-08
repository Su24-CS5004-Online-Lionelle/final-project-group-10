
import student.model.util.NetUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class NetUtilsTest {

    @Test
    public void testGetCharacterUrl() {
        try {
            String name = "Rick Sanchez";
            String status = "Alive";
            String species = "Human";
            String gender = "Male";

            String result = NetUtils.getCharacterUrl(name, status, species, gender);
            assertNotNull(result);
            assertEquals("https://rickandmortyapi.com/api/character/"
            + "?name=Rick+Sanchez&status=Alive&species=Human&gender=Male", result);

            System.out.println("API Response: " + result);
        } catch (IOException | InterruptedException e) {
            fail("Exception during API call: " + e.getMessage());
        }
    }

    @Test
    public void testGetCharacterUrlWithNullName() {
        try {
            String result = NetUtils.getCharacterUrl(null, "Alive", "Human", "Male");
            assertNotNull(result);
            assertEquals("https://rickandmortyapi.com/api/character/?status=Alive&species=Human&gender=Male", result);
        } catch (Exception e) {
            fail("Should handle null name gracefully.");
        }
    }

    @Test
    public void testGetCharacterUrlWithInvalidName() {
        try {
            String result = NetUtils.getCharacterUrl("InvalidName", "Alive", "Human", "Male");
            assertEquals("https://rickandmortyapi.com/api/character/?name=InvalidName&status=Alive&species=Human&gender=Male", result);
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown for invalid names.");
        }
    }

    @Test
    public void testGetCharacterUrlPerformance() {
        long startTime = System.currentTimeMillis();
        try {
            NetUtils.getCharacterUrl("Rick", "Alive", "Human", "Male");
        } catch (Exception e) {
            fail("Exception should not be thrown during performance test.");
        }
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 1000);
    }

    @Test
    public void testGetCharacterData() {
        try {
            String url = NetUtils.getCharacterUrl("Rick Sanchez", "Alive", "Human", "Male");
            String result = NetUtils.getCharacterData(url);
            assertNotNull(result);
            assertTrue(result.contains("Rick Sanchez"));
            assertTrue(result.contains("\"status\":\"Alive\""));
            assertTrue(result.contains("\"species\":\"Human\""));
            assertTrue(result.contains("\"gender\":\"Male\""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testGetCharacterDataInvalidUrl() {
        try {
            String result = NetUtils.getCharacterData("https://rickandmortyapi.com/api/character/invalid");
            assertNotNull(result);
            assertTrue(result.contains("error"));
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown for invalid URLs.");
        }
    }
}
