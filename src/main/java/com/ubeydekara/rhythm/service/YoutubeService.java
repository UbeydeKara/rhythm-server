package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.constant.YoutubeConstants;
import com.ubeydekara.rhythm.model.VideoSource;
import com.ubeydekara.rhythm.repository.VideoSourceRepository;
import com.ubeydekara.rhythm.response.rest.YoutubeSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YoutubeService {

    private final RestTemplate restTemplate;
    private final VideoSourceRepository videoSourceRepository;

    public String getVideoSource(String trackId, String query) {
        Optional<VideoSource> videoSource = videoSourceRepository.findById(trackId);

        if (videoSource.isEmpty())
            return addVideoSource(trackId, query);

        return videoSource.get().getVideoId();
    }

    private String addVideoSource(String trackId, String query) {

        // get videoId from Youtube
        String postUrl = YoutubeConstants.searchEndpoint + "&q=" + query;
        YoutubeSearch youtubeSearch = restTemplate.exchange(postUrl, HttpMethod.GET, null, YoutubeSearch.class).getBody();

        if (youtubeSearch == null)
            throw new RuntimeException();

        // save videoId to db
        VideoSource videoSource = VideoSource.builder()
                .videoId(youtubeSearch.getVideoId())
                .trackId(trackId)
                .build();
        videoSourceRepository.save(videoSource);

        return videoSource.getVideoId();
    }
}
