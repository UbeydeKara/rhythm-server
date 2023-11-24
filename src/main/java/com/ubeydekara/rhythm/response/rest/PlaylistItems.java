package com.ubeydekara.rhythm.response.rest;

import com.ubeydekara.rhythm.response.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistItems {
    private List<Item> items;

    @Data
    public static class Item {
        private Track track;

        @Data
        public static class Track {
            private String id;
            private String name;
            private Album album;

            @Data
            public static class Album {
                private String id;
                private String name;
                private List<Image> images;
                private List<Artist> artists;

                @Data
                public static class Image {
                    private String url;
                }
            }
        }
    }
}
