package student.model.util;

import student.model.ICharacter.CharacterRecord;
import java.util.stream.Stream;

/**
 * A utility class to filter a stream of CharacterRecords.
 * CharacterRecords will be able to be filtered based on
 * character status, species, gender, and episode number.
 */
public class Filter {
    /**
     * Filters a stream of CharacterRecords by status.
     *
     * @param characters The stream of CharacterRecords to filter.
     * @param status The status to filter by.
     * @return A stream of CharacterRecords that have the specified status.
     */
    public Stream<CharacterRecord> filterByStatus(Stream<CharacterRecord> characters, String status) {
        return characters.filter(record -> record.status().equals(status));
    }

    /**
     * Filters a stream of CharacterRecords by species.
     *
     * @param characters The stream of CharacterRecords to filter.
     * @param species The species to filter by.
     * @return A stream of CharacterRecords that are the specified species.
     */
    public Stream<CharacterRecord> filterBySpecies(Stream<CharacterRecord> characters, String species) {
        return characters.filter(record -> record.species().equals(species));
    }

    /**
     * Filters a stream of CharacterRecords by gender.
     *
     * @param characters The stream of CharacterRecords to filter.
     * @param gender The gender to filter by.
     * @return A stream of CharacterRecords that are the specified
     */
    public Stream<CharacterRecord> filterByGender(Stream<CharacterRecord> characters, String gender) {
        return characters.filter(record -> record.gender().equals(gender));
    }

    /**
     * Filters a stream of CharacterRecords by episode.
     *
     * @param characters The stream of CharacterRecords to filter.
     * @param episode The episode number to filter by.
     * @return A stream of CharacterRecords that are in the specified episode.
     */
    public Stream<CharacterRecord> filterByEpisode(Stream<CharacterRecord> characters, String episode) {
        return characters.filter(character -> {
            for (String ep : character.episode()) {
                if (ep.endsWith(episode)) {
                    return true;
                }
            }
            return false;
        });
    }
}
