import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


import student.model.ICharacter.CharacterRecord;
import student.model.util.Sorter;

/**
 * Tests for the Sorter class to ensure that it correctly sorts 
 * a list of CharacterRecords based on the character's name, 
 * in either ascending or descending order.
 */
public class SorterTest {

    /**
     * Tests sorting in ascending order
     * Verifies that a list of characters is sorted correctly in ascending order by name.
     */
    @Test
    public void testSortAscending() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Rick", "Alive", "Human", "Male", "url1"),
            new CharacterRecord(2, "Morty", "Alive", "Human", "Male", "url2"),
            new CharacterRecord(3, "Beth", "Alive", "Human", "Female", "url3")
        );

        List<CharacterRecord> sorted = sorter.sort(characters.stream(), true).collect(Collectors.toList());

        assertEquals("Beth", sorted.get(0).name());
        assertEquals("Morty", sorted.get(1).name());
        assertEquals("Rick", sorted.get(2).name());
    }

    /**
     * Tests sorting in descending order
     * Verifies that a list of characters is sorted correctly in descending order by name.
     */
    @Test
    public void testSortDescending() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Rick", "Alive", "Human", "Male", "url1"),
            new CharacterRecord(2, "Morty", "Alive", "Human", "Male", "url2"),
            new CharacterRecord(3, "Beth", "Alive", "Human", "Female", "url3")
        );

        List<CharacterRecord> sorted = sorter.sort(characters.stream(), false).collect(Collectors.toList());

        assertEquals("Rick", sorted.get(0).name());
        assertEquals("Morty", sorted.get(1).name());
        assertEquals("Beth", sorted.get(2).name());
    }

    /**
     * Tests sorting with an empty list.
     * Ensures that sorting an empty stream results in an empty list without errors.
     */
    @Test
    public void testSortWithEmptyList() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> emptyList = Collections.emptyList();
        List<CharacterRecord> sorted = sorter.sort(emptyList.stream(), true).collect(Collectors.toList());
        assertTrue(sorted.isEmpty());
    }

    /**
     * Tests sorting with a single element in the list.
     * Confirms that sorting a list with only one character does not alter the list 
     * and returns the single character.
     */
    @Test
    public void testSortWithSingleElement() {
        Sorter sorter = new Sorter();
        CharacterRecord singleCharacter = new CharacterRecord(1, "Jerry", "Alive", "Human", "Male", "url4");
        List<CharacterRecord> singleList = Collections.singletonList(singleCharacter);
        List<CharacterRecord> sorted = sorter.sort(singleList.stream(), true).collect(Collectors.toList());
        assertEquals(1, sorted.size());
        assertEquals("Jerry", sorted.get(0).name());
    }

    /**
     * Tests the stability of sorting.
     * Ensures that sorting maintains the order of records 
     * that have identical sort keys, in this case, names.
     */
    @Test
    public void testSortStability() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Summer", "Alive", "Human", "Female", "url6"),
            new CharacterRecord(2, "Summer", "Alive", "Human", "Female", "url7")
        );
        List<CharacterRecord> sorted = sorter.sort(characters.stream(), true).collect(Collectors.toList());
        assertTrue(sorted.get(0).image().equals("url6")
        && sorted.get(1).image().equals("url7"), "Sort should maintain original order of elements with the same name");
    }
}
