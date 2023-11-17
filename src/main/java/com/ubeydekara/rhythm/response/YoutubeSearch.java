package com.ubeydekara.rhythm.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeSearch {
    private List<Item> items;

    @Data
    public static class Item {
        private Id id;

        @Data
        public static class Id {
            private String videoId;
        }
    }

    public String getVideoId() {
        if (items != null)
            return this.items.get(0).id.videoId;
        return "";
    }
}
