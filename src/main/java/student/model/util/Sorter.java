package student.model.util;

import student.model.ICharacter.CharacterRecord;
import java.util.stream.Stream;
import java.util.Comparator;

/**
 * A utility class to sort a stream of CharacterRecords.
 * CharacterRecords will be able to be sorted in ascending or descending order.
 */
public class Sorter {
    /**
     * Sorts a stream of CharacterRecords in ascending or descending order.
     *
     * @param characters The stream of CharacterRecords to sort.
     * @param ascending True if the records should be sorted in ascending order, false otherwise.
     * @return A stream of CharacterRecords sorted in the specified order.
     */
    public Stream<CharacterRecord> sort(Stream<CharacterRecord> characters, boolean ascending) {
        Comparator<CharacterRecord> comparator = Comparator.comparing(CharacterRecord::name, String.CASE_INSENSITIVE_ORDER);
        if (ascending) {
            return characters.sorted(comparator);
        } else {
            return characters.sorted(comparator.reversed());
        }
    }
}
