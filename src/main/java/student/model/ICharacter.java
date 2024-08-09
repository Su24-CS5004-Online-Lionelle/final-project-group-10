package student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Interface to the model.
 **/

public interface ICharacter {

    /**
     * Get the current page no.
     *
     * @return Current page no.
     */
    int getCurrIndex();

    /**
     * Go to the next page by increasing the page number by 1.
     */
    void increasePages();

    /**
     * Go to the previous page by decreasing the page number by 1.
     */
    void decreasePages();

    /**
     * Set the current page num to currIndex.
     *
     * @param index The page number to go to.
     */
    void setCurrIndex(int index);

    /**
     * Load the characters from the API based on the given parameters.
     * The records will be filtered and sorted based on the given parameters.
     * The records will be stored in the characterRecords field.
     * If the API returns an empty list, characterRecords will be set to an empty list.
     * If there is more than one page of results, the method will continue to load the next page until
     * there are no more pages.
     * Throws a RuntimeException if there is an IOException.
     * Throws an InterruptedException if the thread is interrupted.
     *
     * @param name      the name of the character
     * @param status    the status of the character
     * @param species   the species of the character
     * @param gender    the gender of the character
     * @param ascending true if the records should be sorted in ascending order, false otherwise.
     * @return a list of CharacterRecords
     *
     */
    List<CharacterRecord> loadCharacters(String name, String status, String species, String gender, boolean ascending);

    /**
     * Get characters.
     *
     * @return a list of CharacterRecords
     */
    List<CharacterRecord> getCharacterRecords();

    /**
     * Load urls based on searching criteria.
     *
     * @param name      Searched name
     * @param status    Searched status
     * @param species   Searched species
     * @param gender    Searched gender
     * @param ascending Sorting criterion
     */
    void loadURL(String name, String status, String species, String gender, boolean ascending);

    /**
     * Get the url at pos num in the url list.
     *
     * @param num Position of the url to be returned.
     * @return The url at pos num.
     */
    String getURL(int num);

    /**
     * Get the characterRecords on the current page, in ascending or descending order.
     *
     * @param ascending The order.
     * @return A list of characterRecords based on the input order.
     */
    List<CharacterRecord> getCharByPage(boolean ascending);

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
