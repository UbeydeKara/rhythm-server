package com.ubeydekara.rhythm.controller;

import com.ubeydekara.rhythm.request.VideoRequest;
import com.ubeydekara.rhythm.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/youtube")
@CrossOrigin(origins = "*")
public class YoutubeController {
    private final YoutubeService youtubeService;

    @PostMapping("/find")
    public String findVideoId(@RequestBody VideoRequest videoRequest) {
        return youtubeService.getVideoSource(videoRequest.getTrackId(), videoRequest.getSearchQuery());
    }
}
