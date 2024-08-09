import student.model.util.NetUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 * Tests for the NetUtils class that interacts with the Rick and Morty API.
 * Contains various test cases to ensure the functionality of
 * constructing URLs and retrieving data based on different input parameters.
 */
public class NetUtilsTest {

    /**
     * Tests if the getCharacterUrl method correctly constructs the URL
     * with given character details.
     * Asserts that the resultant URL is not null and matches the expected format.
     */
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

    /**
     * Tests if the getCharacterUrl method handles null name input
     * Asserts that the resultant URL is not null 
     * and conforms to expected structure when name is omitted.
     */
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

    /**
     * Tests the URL construction with an invalid character name.
     * Asserts that the method does not throw an exception 
     * and the URL includes the invalid name.
     */
    @Test
    public void testGetCharacterUrlWithInvalidName() {
        try {
            String result = NetUtils.getCharacterUrl("InvalidName", "Alive", "Human", "Male");
            assertEquals("https://rickandmortyapi.com/api/character/?name=InvalidName"
                                   + "&status=Alive&species=Human&gender=Male", result);
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown for invalid names.");
        }
    }

    /**
     * Performance test for the getCharacterUrl method.
     * Measures the execution time to ensure it completes within a specified time frame.
     */
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

    /**
     * Tests the retrieval of character data from the API.
     * Asserts that the data contains expected fields and values based on the input parameters.
     */
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
    
    /**
     * Tests if the getCharacterData method can handle an invalid URL.
     * Asserts that no exception is thrown and the response contains error information.
     */
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
