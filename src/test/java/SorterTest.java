import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


import student.model.ICharacter.CharacterRecord;
import student.model.util.Sorter;

public class SorterTest {

    @Test
    public void testSortAscending() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Rick", "Alive", "Human", "Male", "url1", Arrays.asList("1", "2")),
            new CharacterRecord(2, "Morty", "Alive", "Human", "Male", "url2", Arrays.asList("1", "3")),
            new CharacterRecord(3, "Beth", "Alive", "Human", "Female", "url3", Arrays.asList("2", "4"))
        );

        List<CharacterRecord> sorted = sorter.sort(characters.stream(), true).collect(Collectors.toList());

        assertEquals("Beth", sorted.get(0).name());
        assertEquals("Morty", sorted.get(1).name());
        assertEquals("Rick", sorted.get(2).name());
    }

    @Test
    public void testSortDescending() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Rick", "Alive", "Human", "Male", "url1", Arrays.asList("1", "2")),
            new CharacterRecord(2, "Morty", "Alive", "Human", "Male", "url2", Arrays.asList("1", "3")),
            new CharacterRecord(3, "Beth", "Alive", "Human", "Female", "url3", Arrays.asList("2", "4"))
        );

        List<CharacterRecord> sorted = sorter.sort(characters.stream(), false).collect(Collectors.toList());

        assertEquals("Rick", sorted.get(0).name());
        assertEquals("Morty", sorted.get(1).name());
        assertEquals("Beth", sorted.get(2).name());
    }

    @Test
    public void testSortWithEmptyList() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> emptyList = Collections.emptyList();
        List<CharacterRecord> sorted = sorter.sort(emptyList.stream(), true).collect(Collectors.toList());
        assertTrue(sorted.isEmpty());
    }

    @Test
    public void testSortWithSingleElement() {
        Sorter sorter = new Sorter();
        CharacterRecord singleCharacter = new CharacterRecord(1, "Jerry", "Alive", "Human", "Male", "url4", Arrays.asList("1", "2"));
        List<CharacterRecord> singleList = Collections.singletonList(singleCharacter);
        List<CharacterRecord> sorted = sorter.sort(singleList.stream(), true).collect(Collectors.toList());
        assertEquals(1, sorted.size());
        assertEquals("Jerry", sorted.get(0).name());
    }

    @Test
    public void testSortStability() {
        Sorter sorter = new Sorter();
        List<CharacterRecord> characters = Arrays.asList(
            new CharacterRecord(1, "Summer", "Alive", "Human", "Female", "url6", Arrays.asList("6", "7")),
            new CharacterRecord(2, "Summer", "Alive", "Human", "Female", "url7", List.of("8"))
        );
        List<CharacterRecord> sorted = sorter.sort(characters.stream(), true).collect(Collectors.toList());
        assertTrue(sorted.get(0).image().equals("url6") && sorted.get(1).image().equals("url7"), "Sort should maintain original order of elements with the same name");
    }
}
