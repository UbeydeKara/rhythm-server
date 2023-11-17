package com.ubeydekara.rhythm.util;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.SpotifyToken;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@UtilityClass
public class SpotifyAuth {
    private final RestTemplate restTemplate = new RestTemplate();
    private static SpotifyToken spotifyToken;

    public static SpotifyToken getToken() {

        if (spotifyToken == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

            MultiValueMap<String, String> bodyPair = new LinkedMultiValueMap<>();
            bodyPair.add("grant_type", SpotifyConstants.grantType);
            bodyPair.add("client_id", SpotifyConstants.clientId);
            bodyPair.add("client_secret", SpotifyConstants.clientSecret);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodyPair, httpHeaders);
            spotifyToken = restTemplate.exchange(SpotifyConstants.tokenUrl, HttpMethod.POST, entity, SpotifyToken.class).getBody();
        }

        return spotifyToken;
    }
}
