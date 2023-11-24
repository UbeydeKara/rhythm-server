package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.request.VideoRequest;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.service.SpotifyService;
import com.ubeydekara.rhythm.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
@CrossOrigin(origins = "*")
public class MusicController {
    private final SpotifyService spotifyService;
    private final YoutubeService youtubeService;

    @GetMapping("/top50")
    public List<Track> getTop50() {
        return spotifyService.getPlaylistTracks(SpotifyConstants.global50PlaylistId);
    }

    @GetMapping("/newReleases")
    public List<Track> getNewReleases() {
        return spotifyService.getPlaylistTracks(SpotifyConstants.globalNewReleasesPlaylistId);
    }

    @PostMapping("/find/videoId")
    public String findVideoId(@RequestBody VideoRequest videoRequest) {
        return youtubeService.getVideoSource(videoRequest.getTrackId(), videoRequest.getSearchQuery());
    }
}
