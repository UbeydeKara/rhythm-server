package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.datautils.ChartUtils;
import com.ubeydekara.rhythm.datautils.response.ChartData;
import com.ubeydekara.rhythm.datautils.response.ChartResponse;
import com.ubeydekara.rhythm.model.WeeklyAlbum;
import com.ubeydekara.rhythm.model.WeeklyArtist;
import com.ubeydekara.rhythm.model.WeeklyReleases;
import com.ubeydekara.rhythm.model.WeeklyTrack;
import com.ubeydekara.rhythm.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chart")
@CrossOrigin(origins = "*")
public class ChartController {
    private final ChartUtils chartUtils;
    private final ChartService chartService;

    @GetMapping("/weeklyTracks")
    public List<WeeklyTrack> weeklyTracks() {
        return chartService.weeklyTracks();
    }

    @GetMapping("/weeklyAlbums")
    public List<WeeklyAlbum> weeklyAlbums() {
        return chartService.weeklyAlbums();
    }

    @GetMapping("/weeklyArtists")
    public List<WeeklyArtist> weeklyArtists() {
        return chartService.weeklyArtists();
    }

    @GetMapping("/weeklyReleases")
    public List<WeeklyReleases> weeklyReleases() {
        return chartService.weeklyReleases();
    }

    @PostMapping("/import")
    public ChartResponse importData(@RequestBody ChartData chartData) {
        return chartUtils.updateCharts(chartData);
    }

    @PostMapping("/import/weeklyReleases")
    public List<WeeklyReleases> importWeeklyReleases() {
        return chartUtils.importWeeklyReleases();
    }
}
