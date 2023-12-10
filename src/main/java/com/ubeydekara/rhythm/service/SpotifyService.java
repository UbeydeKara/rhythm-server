package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.AlbumDetail;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.response.rest.PlaylistItems;
import com.ubeydekara.rhythm.response.rest.AlbumDetailRest;
import com.ubeydekara.rhythm.util.SpotifyAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final RestTemplate restTemplate;

    public List<Track> getPlaylistTracks(String playlistId) {
        String requestUrl = SpotifyConstants.playlistTracksEndpoint.replace("{playlist_id}", playlistId);

        PlaylistItems playlistItems = restTemplate
                .exchange(requestUrl, HttpMethod.GET, getHttpEntity(), PlaylistItems.class)
                .getBody();

        if (playlistItems == null)
            throw new RuntimeException();

        return preparePlaylist(playlistItems);
    }

    public AlbumDetail getAlbumDetail(String albumId) {
        String requestUrl = SpotifyConstants.albumDetailEndpoint.replace("{album_id}", albumId);

        AlbumDetailRest albumDetailRest = restTemplate
                .exchange(requestUrl, HttpMethod.GET, getHttpEntity(), AlbumDetailRest.class)
                .getBody();

        if (albumDetailRest == null)
            throw new RuntimeException();

        return this.prepareAlbumDetail(albumDetailRest);
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

    private AlbumDetail prepareAlbumDetail(AlbumDetailRest albumDetailRest) {
        return AlbumDetail.builder()
                .name(albumDetailRest.getName())
                .image(albumDetailRest.getImages().get(0).getUrl())
                .artists(albumDetailRest.getArtists())
                .tracks(albumDetailRest.getTracks().getItems()
                        .stream().sorted(Comparator.comparing(AlbumDetailRest.Track.Item::getTrackNumber))
                        .toList())
                .genres(albumDetailRest.getGenres())
                .totalTracks(albumDetailRest.getTotalTracks())
                .build();
    }

    private HttpEntity<Object> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", SpotifyAuth.getToken().toString());
        return new HttpEntity<>(httpHeaders);
    }
}
