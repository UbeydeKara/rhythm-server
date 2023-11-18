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
            private Album album;
        }
    }

    public List<Album> getAlbums() {
        return items.stream().map(x -> x.track.album).toList();
    }
}
