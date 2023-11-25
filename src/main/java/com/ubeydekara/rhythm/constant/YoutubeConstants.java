package com.ubeydekara.rhythm.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YoutubeConstants {
    public static String searchEndpoint = "https://youtube.googleapis.com/youtube/v3/search?part=id&maxResults=1&type=video&key=";

    @Value("${client.youtube.apikey}")
    private String apikey;

    @Value("${client.youtube.apikey}")
    public void setApikey(String apikey) {
        YoutubeConstants.searchEndpoint = searchEndpoint + apikey;
    }
}
