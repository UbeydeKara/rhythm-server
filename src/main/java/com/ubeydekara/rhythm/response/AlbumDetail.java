package com.ubeydekara.rhythm.response;

import com.ubeydekara.rhythm.response.rest.AlbumDetailRest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDetail {
    private String name;
    private String image;
    private List<Artist> artists;
    private List<AlbumDetailRest.Track.Item> tracks;
    private List<String> genres;
    private Byte totalTracks;
}
