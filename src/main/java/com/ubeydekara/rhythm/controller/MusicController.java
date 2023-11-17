package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.response.Album;
import com.ubeydekara.rhythm.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
public class MusicController {
    private final SpotifyService spotifyService;

    @GetMapping("/top50")
    public List<Album> getTop50() {
        return spotifyService.getAlbumTracks(SpotifyConstants.global50PlaylistId);
    }
}
