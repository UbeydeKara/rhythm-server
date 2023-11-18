package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.Album;
import com.ubeydekara.rhythm.response.PlaylistItems;
import com.ubeydekara.rhythm.response.SpotifyToken;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.util.SpotifyAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final RestTemplate restTemplate;
    private final YoutubeService youtubeService;

    public List<Track> getPlaylistTracks(String albumId) {
        SpotifyToken token = SpotifyAuth.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", token.toString());

        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        String postUrl = SpotifyConstants.playlistTracksEndpoint.replace("{playlist_id}", albumId);

        PlaylistItems playlistItems = restTemplate.exchange(postUrl, HttpMethod.GET, entity, PlaylistItems.class).getBody();

        if (playlistItems == null)
            throw new RuntimeException();

        return preparePlaylist(playlistItems);
    }

    private List<Track> preparePlaylist(PlaylistItems playlistItems) {
        List<Album> albums = playlistItems.getAlbums();
        List<Track> tracks = new ArrayList<>(albums.size());

        for (Album album : albums) {
            String artists = String.join(", ", album.getArtists().stream().map(Album.Artist::getName).toList());
            String images = album.getImages().stream().map(Album.Image::getUrl).toList().get(0);

            Track track = Track.builder()
                    .id(album.getId())
                    .artists(artists)
                    .image(images)
                    .name(album.getName())

                    // insert videoId from YouTube
                    .ytVideoId(youtubeService.getVideoSource(album.getId(), album.getName()))
                    .build();

            tracks.add(track);
        }

        return tracks;
    }
}
