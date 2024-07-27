package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.model.util.NetUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * Updates the characterRecords field with the new data.
     *
     * @param name     the name of the character
     * @param status   the status of the character
     * @param species  the species of the character
     * @param gender   the gender of the character
     * @param episodes the episode(s) the character is in
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
            }

            Comparator<CharacterRecord> comparator = Comparator.comparing(it -> it.name(), String.CASE_INSENSITIVE_ORDER);
            if (!ascending) {
                comparator = comparator.reversed();
            }

            this.characterRecords = mapper.readValue(infoNode.toString(), new TypeReference<List<CharacterRecord>>() {
            }).stream().sorted(comparator).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
