package org.duckdns.aqueipo.kafkademo.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The type Rest service.
 */
@Service
public class RestService {

    private final Logger logger = LoggerFactory.getLogger(RestService.class);
    private final String API_URL = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

    private LocalDateTime before, after;
    private Duration duration;

    private String getUnirest(String url) throws UnirestException {
        before = LocalDateTime.now();
        HttpResponse<JsonNode> jsonResponse = Unirest.get(API_URL).asJson();
        after = LocalDateTime.now();
        duration = Duration.between(before, after);
        logger.info(String.format("$$ -> UNIREST Request duration (ms): %d", duration.toMillis()));

        return jsonResponse.getBody().toString();
    }

    private String getOkHttp(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        before = LocalDateTime.now();
        Response response = client.newCall(request).execute();
        after = LocalDateTime.now();
        duration = Duration.between(before, after);
        logger.info(String.format("$$ -> OKHTTP Request duration (ms): %d", duration.toMillis()));

        return response.body().string();
    }

    /**
     * Get string.
     *
     * @param url    the url
     * @param client the client
     * @return the string
     * @throws IOException      the io exception
     * @throws UnirestException the unirest exception
     */
    public String get(String url, String client) throws IOException, UnirestException {
        if ("unirest".equals(client)) {
            return getUnirest(url);
        } else if ("okhttp".equals(client)) {
            return getOkHttp(url);
        }

        return null;
    }

}
