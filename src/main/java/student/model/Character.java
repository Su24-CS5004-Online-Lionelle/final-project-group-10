package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.model.util.NetUtils;
import student.model.util.Sorter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.net.URL;

public class Character implements ICharacter {

    /**
     * The list of character records.
     **/
    private List<CharacterRecord> characterRecords = new ArrayList<>();
    private List<String> selectedURLs = new ArrayList<>();
    private int pages;
    private int currIndex = 0;

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public void increasePages() {
        currIndex ++;
    }

    public void decreasePages() {
        currIndex --;
    }

    public Character() {
        // empty for now
    }

    @Override
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
     * @param name      the name of the character
     * @param status    the status of the character
     * @param species   the species of the character
     * @param gender    the gender of the character
     * @param ascending true if the records should be sorted in ascending order, false otherwise.
     */
    @Override
    public List<CharacterRecord> loadCharacters(String name, String status, String species, String gender, boolean ascending) {
        try {
            List<CharacterRecord> characters = new ArrayList<>();
            for (String url : this.selectedURLs) {
                String response = NetUtils.getCharacterData(url);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response);
                JsonNode infoNode = rootNode.path("results");
                if (Objects.isNull(infoNode) || infoNode.isEmpty()) {
                    break;
                }
                List<CharacterRecord> pageRecords = mapper.readValue(infoNode.toString(),
                        new TypeReference<List<CharacterRecord>>() {
                        });
                characters.addAll(pageRecords);
            }
//            String nextUrl = NetUtils.getCharacterUrl(name, status, species, gender);
//
//            while (nextUrl != null) {
//                String response = NetUtils.getCharacterData(nextUrl);
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode rootNode = mapper.readTree(response);
//                JsonNode infoNode = rootNode.path("results");
//                if (Objects.isNull(infoNode) || infoNode.isEmpty()) {
//                    break;
//                }
//
//                List<CharacterRecord> pageRecords = mapper.readValue(infoNode.toString(),
//                        new TypeReference<List<CharacterRecord>>() {
//                        });
//                characters.addAll(pageRecords);
//                nextUrl = rootNode.path("info").path("next").asText(null);
//            }

            characterRecords = new Sorter().sort(characters.stream(), ascending).collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return characterRecords;
    }

    @Override
    public void loadURL(String name, String status, String species, String gender, boolean ascending) {
        try {
            this.selectedURLs.clear();
            String currURL = NetUtils.getCharacterUrl(name, status, species, gender);

            while (currURL != null) {
                this.selectedURLs.add(currURL);
                String response = NetUtils.getCharacterData(currURL);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response);
                currURL = rootNode.path("info").path("next").asText(null);
            }
            this.pages = this.selectedURLs.size();
            if (!ascending) {
                Collections.reverse(this.selectedURLs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getURL(int index) {
        if (index < 0 || index >= this.pages) {
            return null;
        }
        return selectedURLs.get(index);
    }


    @Override
    public List<CharacterRecord> getCharByPage(boolean ascending) {
        String currUrl = this.getURL(currIndex);
        List<CharacterRecord> characters = new ArrayList<>();
        try {
            String response = NetUtils.getCharacterData(currUrl);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode infoNode = rootNode.path("results");
            characters = mapper.readValue(infoNode.toString(), new TypeReference<List<CharacterRecord>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        characters = new Sorter().sort(characters.stream(), ascending).collect(Collectors.toList());
        return characters;
    }

    public ImageIcon getImageIcon(CharacterRecord characterRecord) {
        try {
            URL url = new URL(characterRecord.image());
            ImageIcon originalIcon = new ImageIcon(url);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
