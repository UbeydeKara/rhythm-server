package com.ubeydekara.rhythm.response.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubeydekara.rhythm.response.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDetailRest {
    private String name;
    private List<Image> images;
    private List<Artist> artists;
    private Track tracks;
    private List<String> genres;

    @JsonProperty("total_tracks")
    private Byte totalTracks;

    @Data
    public static class Track {
        private List<Item> items;

        @Data
        public static class Item {
            private String id;
            private String name;

            @JsonProperty("duration_ms")
            private Integer durationMs;

            @JsonProperty("track_number")
            private Byte trackNumber;
        }
    }

    @Data
    public static class Image {
        private String url;
    }
}
