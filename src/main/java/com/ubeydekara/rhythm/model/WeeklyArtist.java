package com.ubeydekara.rhythm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("weekly_artists")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeeklyArtist {

    @Id
    private String id;

    private String name;
    private String image;

    private Integer currentRank;
    private Integer previousRank;
}
