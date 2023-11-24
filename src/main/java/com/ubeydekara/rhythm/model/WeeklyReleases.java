package com.ubeydekara.rhythm.model;

import com.ubeydekara.rhythm.response.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("weekly_releases")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeeklyReleases {

    @Id
    private String id;

    private String name;
    private String image;
    private List<Artist> artists;
}
