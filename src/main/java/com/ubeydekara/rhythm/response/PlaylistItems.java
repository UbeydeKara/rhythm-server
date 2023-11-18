package com.ubeydekara.rhythm.response;

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

                @Data
                public static class Artist {
                    private String name;
                }
            }
        }
    }
}
