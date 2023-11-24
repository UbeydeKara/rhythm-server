package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.response.rest.PlaylistItems;
import com.ubeydekara.rhythm.util.SpotifyAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final RestTemplate restTemplate;

    public List<Track> getPlaylistTracks(String albumId) {
        String postUrl = SpotifyConstants.playlistTracksEndpoint.replace("{playlist_id}", albumId);

        PlaylistItems playlistItems = restTemplate
                .exchange(postUrl, HttpMethod.GET, getHttpEntity(), PlaylistItems.class)
                .getBody();

        if (playlistItems == null)
            throw new RuntimeException();

        return preparePlaylist(playlistItems);
    }

    private List<Track> preparePlaylist(PlaylistItems playlistItems) {

        List<PlaylistItems.Item.Track> tracks = playlistItems
                .getItems()
                .stream()
                .map(PlaylistItems.Item::getTrack)
                .toList();

        List<Track> trackListResponse = new ArrayList<>(tracks.size());

        for (PlaylistItems.Item.Track track : tracks) {

            String image = track
                    .getAlbum()
                    .getImages()
                    .stream()
                    .map(PlaylistItems.Item.Track.Album.Image::getUrl)
                    .toList()
                    .get(0);

            Track trackResponse = Track.builder()
                    .id(track.getId())
                    .name(track.getName())
                    .artists(track.getAlbum().getArtists())
                    .image(image)
                    .build();

            trackListResponse.add(trackResponse);
        }

        return trackListResponse;
    }

    private HttpEntity<Object> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", SpotifyAuth.getToken().toString());
        return new HttpEntity<>(httpHeaders);
    }
}
