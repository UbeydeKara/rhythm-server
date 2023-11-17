package com.ubeydekara.rhythm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "video_sources")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoSource {
    @Id
    private String trackId;
    private String videoId;
}
