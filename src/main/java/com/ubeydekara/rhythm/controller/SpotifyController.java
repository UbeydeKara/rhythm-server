package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.response.AlbumDetail;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spotify")
@CrossOrigin(origins = "*")
public class SpotifyController {
    private final SpotifyService spotifyService;

    @GetMapping("/playlist/{id}")
    public List<Track> getPlaylistTracks(@PathVariable String id) {
        return spotifyService.getPlaylistTracks(id);
    }

    @GetMapping("/album/{id}")
    public AlbumDetail getAlbumDetail(@PathVariable String id) {
        return spotifyService.getAlbumDetail(id);
    }
}
