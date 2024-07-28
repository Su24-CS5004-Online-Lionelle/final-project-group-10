package student.model.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class NetUtils {
    private static final String BASE_API_URL = "https://rickandmortyapi.com/api/character/";

    private NetUtils() {
        // This class should not be instantiated
    }

    /**
     * Look up information from the API by character name, status, species, gender, and episode number.
     *
     * @param name The character name.
     * @param status The character's status (dead, alive, unknown).
     * @param species The character's species.
     * @param gender The character's gender.
     * @param episodes The episodes in which the character appears.
     * @return string URL containing API response.
     * @throws IOException if search is not successful.
     */
    public static String getCharacterData(String name, String status, String species, String gender, List<String> episodes) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        List<String> queryParams = new ArrayList<>();

        if (name != null && !name.isEmpty()) queryParams.add("name=" + URLEncoder.encode(name, StandardCharsets.UTF_8));
        if (status != null && !status.isEmpty()) queryParams.add("status=" + URLEncoder.encode(status, StandardCharsets.UTF_8));
        if (species != null && !species.isEmpty()) queryParams.add("species=" + URLEncoder.encode(species, StandardCharsets.UTF_8));
        if (gender != null && !gender.isEmpty()) queryParams.add("gender=" + URLEncoder.encode(gender, StandardCharsets.UTF_8));
        if (episodes != null && !episodes.isEmpty()) {
            for (String episode : episodes) {
                queryParams.add("episode=" + URLEncoder.encode(episode, StandardCharsets.UTF_8));
            }
        }

        String queryString = String.join("&", queryParams);
        String finalUrl = BASE_API_URL + (queryString.isEmpty() ? "" : "?" + queryString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}