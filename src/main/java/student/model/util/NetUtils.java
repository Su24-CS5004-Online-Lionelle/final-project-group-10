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
     * @return string URL containing API response.
     * @throws IOException if search is not successful.
     */
    public static String getCharacterUrl(String name, String status, String species, String gender) throws IOException, InterruptedException {
        List<String> queryParams = new ArrayList<>();

        if (name != null && !name.isEmpty()) queryParams.add("name=" + URLEncoder.encode(name, StandardCharsets.UTF_8));
        if (status != null && !status.isEmpty()) queryParams.add("status=" + URLEncoder.encode(status, StandardCharsets.UTF_8));
        if (species != null && !species.isEmpty()) queryParams.add("species=" + URLEncoder.encode(species, StandardCharsets.UTF_8));
        if (gender != null && !gender.isEmpty()) queryParams.add("gender=" + URLEncoder.encode(gender, StandardCharsets.UTF_8));

        String queryString = String.join("&", queryParams);
        return BASE_API_URL + (queryString.isEmpty() ? "" : "?" + queryString);
    }

    /**
     * Get the response body from the API after building the URL.
     * 
     * @param url The URL of the next page.
     * @return The response body.
     * @throws IOException if search is not successful.
     * @throws InterruptedException if the thread is interrupted.
     */
    public static String getCharacterData(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
