package com.ubeydekara.rhythm.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String name;
    private List<Image> images;
    private List<Artist> artists;
    private String ytVideoId;

    @Data
    public static class Image {
        private String url;
    }

    @Data
    public static class Artist {
        private String name;
    }
}
