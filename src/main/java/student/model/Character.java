package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.model.util.NetUtils;
import student.model.util.Sorter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
     * The records will be filtered and sorted based on the given parameters.
     * The records will be stored in the characterRecords field.
     * If the API returns an empty list, characterRecords will be set to an empty list.
     * If there is more than one page of results, the method will continue to load the next page until there are no more pages.
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
//    @Override
//    public void loadCharacters(String name, String status, String species, String gender, List<String> episodes, boolean ascending) {
//        try {
//            String response = NetUtils.getCharacterData(name, status, species, gender, episodes);
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(response);
//            JsonNode infoNode = rootNode.path("results");
//            if (Objects.isNull(infoNode) || infoNode.isEmpty()) {
//                characterRecords = Collections.emptyList();
//                return;
//            }
//
//            List<CharacterRecord> allRecords = mapper.readValue(infoNode.toString(), new TypeReference<List<CharacterRecord>>() {});
//
//            characterRecords = new Sorter().sort(allRecords.stream(), ascending).collect(Collectors.toList());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Override
    public void loadCharacters(String name, String status, String species, String gender, List<String> episodes, boolean ascending) {
        try {
            List<CharacterRecord> characters = new ArrayList<>();
            String nextUrl = NetUtils.getCharacterUrl(name, status, species, gender, episodes);

            while (nextUrl != null) {
                String response = NetUtils.getCharacterData(nextUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response);
                JsonNode infoNode = rootNode.path("results");
                if (Objects.isNull(infoNode) || infoNode.isEmpty()) {
                    break;
                }

                List<CharacterRecord> pageRecords = mapper.readValue(infoNode.toString(), new TypeReference<List<CharacterRecord>>() {
                });
                characters.addAll(pageRecords);

                nextUrl = rootNode.path("info").path("next").asText(null);
            }

            characterRecords = new Sorter().sort(characters.stream(), ascending).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
