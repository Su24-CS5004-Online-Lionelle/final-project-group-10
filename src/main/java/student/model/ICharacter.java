package student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Interface to the model.
 **/

public interface ICharacter {

    void loadCharacters(String name, String status, String species, String gender, boolean ascending);

    /**
     * Character record to pass around between objects.
     * Uses Jackson annotations to map JSON fields to Java fields.
     *
     * @param id      The character's unique identifier. User will not see this.
     *                // not sure if we need to include this in the record
     * @param name    The character's name.
     * @param status  The character's status (alive, dead, unknown).
     * @param species The character's species.
     * @param gender  The character's gender.
     * @param image   The character's image URL.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "results")
    @JsonPropertyOrder({"id", "name", "status", "species", "gender", "image"})
    record CharacterRecord(int id, String name, String status, String species, String gender,
                           String image) {
    }
}
