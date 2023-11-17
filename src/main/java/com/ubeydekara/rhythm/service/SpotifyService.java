package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.Album;
import com.ubeydekara.rhythm.response.PlaylistTracks;
import com.ubeydekara.rhythm.response.SpotifyToken;
import com.ubeydekara.rhythm.util.SpotifyAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final RestTemplate restTemplate;

    public List<Album> getAlbumTracks(String albumId) {
        SpotifyToken token = SpotifyAuth.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", token.toString());

        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        String postUrl = SpotifyConstants.playlistTracksEndpoint.replace("{playlist_id}", albumId);

        return restTemplate.exchange(postUrl, HttpMethod.GET, entity, PlaylistTracks.class).getBody().getAlbums();
    }
}
