package com.ubeydekara.rhythm.constant;

public class SpotifyConstants {

    // auth
    public static String clientId = "<sp_client_id>";
    public static String clientSecret = "<sp_client_secret>";
    public static String tokenUrl = "https://accounts.spotify.com/api/token";
    public static String grantType = "client_credentials";

    // endpoints
    public static String albumTracksEndpoint = "https://api.spotify.com/v1/albums/%s/tracks";
    public static String playlistTracksEndpoint = "https://api.spotify.com/v1/playlists/{playlist_id}/tracks?fields=items.track.album%28name%2Cimages%2Cartists%28name%29%29";

    // ids
    public static String global50PlaylistId = "37i9dQZEVXbMDoHDwVN2tF";
}
