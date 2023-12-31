package com.ubeydekara.rhythm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Track {
    private String id;
    private String name;
    private String image;
    private List<Artist> artists;
}
