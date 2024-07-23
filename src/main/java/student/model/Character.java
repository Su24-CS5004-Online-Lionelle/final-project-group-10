package student.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import student.model.util.NetUtils;
import java.io.IOException;
import java.util.List;

public class Character implements ICharacter {
    /** The list of character records. **/
    private List<CharacterRecord> characterRecords;

    public Character() {
        // empty for now
    }

    /**
     * Load the characters from the API based on the given parameters.
     * Updates the characterRecords field with the new data.
     *
     * @param name the name of the character
     * @param status the status of the character
     * @param species the species of the character
     * @param gender the gender of the character
     * @param episodes the episode(s) the character is in
     */
    @Override
    public void loadCharacters(String name, String status, String species, String gender, List<String> episodes) {
        try {
            String response = NetUtils.getCharacterData(name, status, species, gender, episodes);
            ObjectMapper mapper = new ObjectMapper();
            this.characterRecords = mapper.readValue(response, new TypeReference<List<CharacterRecord>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}