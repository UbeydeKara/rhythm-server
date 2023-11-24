package com.ubeydekara.rhythm.datautils.response;

import com.ubeydekara.rhythm.model.WeeklyAlbum;
import com.ubeydekara.rhythm.model.WeeklyArtist;
import com.ubeydekara.rhythm.model.WeeklyTrack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChartResponse {
    List<WeeklyTrack> weeklyTracks;
    List<WeeklyAlbum> weeklyAlbums;
    List<WeeklyArtist> weeklyArtists;
}
