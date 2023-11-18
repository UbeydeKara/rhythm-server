package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
@CrossOrigin(origins = "*")
public class MusicController {
    private final SpotifyService spotifyService;

    @GetMapping("/top50")
    public List<Track> getTop50() {
        return spotifyService.getPlaylistTracks(SpotifyConstants.global50PlaylistId);
    }
}
