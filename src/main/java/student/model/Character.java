package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.model.util.Filter;
import student.model.util.NetUtils;
import student.model.util.Sorter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Character implements ICharacter {
    /**
     * The list of character records.
     **/
    private List<CharacterRecord> characterRecords;

    public Character() {
        // empty for now
    }

    public List<CharacterRecord> getCharacterRecords() {
        return characterRecords;
    }

    /**
     * Load the characters from the API based on the given parameters.
     * The records will be filtered and sorted based on the given parameters.
     * The records will be stored in the characterRecords field.
     * If the API returns an empty list, characterRecords will be set to an empty list.
     * Throws a RuntimeException if there is an IOException.
     * Throws an InterruptedException if the thread is interrupted.
     *
     * @param name     the name of the character
     * @param status   the status of the character
     * @param species  the species of the character
     * @param gender   the gender of the character
     * @param episodes the episode(s) the character is in
     * @param ascending true if the records should be sorted in ascending order, false otherwise.
     */
    @Override
    public void loadCharacters(String name, String status, String species, String gender, List<String> episodes, boolean ascending) {
        try {
            String response = NetUtils.getCharacterData(name, status, species, gender, episodes);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode infoNode = rootNode.path("results");
            if (Objects.isNull(infoNode) || infoNode.isEmpty()) {
                characterRecords = Collections.emptyList();
                return;
            }

            List<CharacterRecord> allRecords = mapper.readValue(infoNode.toString(), new TypeReference<List<CharacterRecord>>() {});

            Stream<CharacterRecord> filteredStream = allRecords.stream();
            if (status != null) {
                filteredStream = new Filter().filterByStatus(filteredStream, status);
            }
            if (species != null) {
                filteredStream = new Filter().filterBySpecies(filteredStream, species);
            }
            if (gender != null) {
                filteredStream = new Filter().filterByGender(filteredStream, gender);
            }
            if (episodes != null && !episodes.isEmpty()) {
                for (String episode : episodes) {
                    filteredStream = new Filter().filterByEpisode(filteredStream, episode);
                }
            }

            characterRecords = new Sorter().sort(filteredStream, ascending).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
