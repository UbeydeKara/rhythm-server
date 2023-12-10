package com.ubeydekara.rhythm.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConstants {

    // client
    public static String clientId;

    public static String clientSecret;

    @Value("${client.spotify.clientId}")
    public String clientIdHolder;

    @Value("${client.spotify.clientSecret}")
    public String clientSecretHolder;

    @Value("${client.spotify.clientId}")
    public void setClientIdHolder(String clientIdHolder) {
        SpotifyConstants.clientId = clientIdHolder;
    }

    @Value("${client.spotify.clientSecret}")
    public void setClientSecretHolder(String clientSecretHolder) {
        SpotifyConstants.clientSecret = clientSecretHolder;
    }

    // token
    public static String tokenUrl = "https://accounts.spotify.com/api/token";
    public static String grantType = "client_credentials";

    // endpoints
    public static String albumDetailEndpoint = "https://api.spotify.com/v1/albums/{album_id}";
    public static String playlistTracksEndpoint = "https://api.spotify.com/v1/playlists/{playlist_id}/tracks?fields=items.track.album%28name%2Cimages%2Cartists%28name%29%29";

    // ids
    public static String newMusicFridayPlaylistId = "37i9dQZF1DX4JAvHpjipBk";
}
