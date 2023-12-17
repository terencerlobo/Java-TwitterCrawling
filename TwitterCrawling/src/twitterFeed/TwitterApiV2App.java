package twitterFeed;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class TwitterApiV2App {
	
	private static final String BEARER_TOKEN = "BEARER_TOKEN";

    public static void main(String[] args) {
        String query = "java"; 
        String url = "https://api.twitter.com/2/tweets/search/recent?query=" + query;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + BEARER_TOKEN)
            .header("Content-Type", "application/json")
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(t -> {
				try {
					parseResponse(t);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			})
            .join();
    }

    private static void parseResponse(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(4)); 
    }

}
