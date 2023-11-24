package com.ubeydekara.rhythm.service;

import com.ubeydekara.rhythm.model.WeeklyAlbum;
import com.ubeydekara.rhythm.model.WeeklyArtist;
import com.ubeydekara.rhythm.model.WeeklyReleases;
import com.ubeydekara.rhythm.model.WeeklyTrack;
import com.ubeydekara.rhythm.repository.WeeklyAlbumsRepository;
import com.ubeydekara.rhythm.repository.WeeklyArtistsRepository;
import com.ubeydekara.rhythm.repository.WeeklyReleasesRepository;
import com.ubeydekara.rhythm.repository.WeeklyTracksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final WeeklyTracksRepository weeklyTracksRepository;
    private final WeeklyAlbumsRepository weeklyAlbumsRepository;
    private final WeeklyArtistsRepository weeklyArtistsRepository;
    private final WeeklyReleasesRepository weeklyReleasesRepository;

    public List<WeeklyTrack> weeklyTracks() {
        return weeklyTracksRepository.findAll();
    }

    public List<WeeklyTrack> weeklyTracks(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return weeklyTracksRepository.findAll(paging).stream().toList();
    }

    public List<WeeklyAlbum> weeklyAlbums() {
        return weeklyAlbumsRepository.findAll();
    }

    public List<WeeklyAlbum> weeklyAlbums(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return weeklyAlbumsRepository.findAll(paging).stream().toList();
    }

    public List<WeeklyArtist> weeklyArtists() {
        return weeklyArtistsRepository.findAll();
    }

    public List<WeeklyArtist> weeklyArtists(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return weeklyArtistsRepository.findAll(paging).stream().toList();
    }

    public List<WeeklyReleases> weeklyReleases() {
        return weeklyReleasesRepository.findAll();
    }

    public List<WeeklyReleases> weeklyReleases(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return weeklyReleasesRepository.findAll(paging).stream().toList();
    }
}
