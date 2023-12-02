package com.ubeydekara.rhythm.datautils;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import com.ubeydekara.rhythm.datautils.response.ChartData;
import com.ubeydekara.rhythm.datautils.response.ChartResponse;
import com.ubeydekara.rhythm.model.WeeklyAlbum;
import com.ubeydekara.rhythm.model.WeeklyArtist;
import com.ubeydekara.rhythm.model.WeeklyReleases;
import com.ubeydekara.rhythm.model.WeeklyTrack;
import com.ubeydekara.rhythm.repository.WeeklyAlbumsRepository;
import com.ubeydekara.rhythm.repository.WeeklyArtistsRepository;
import com.ubeydekara.rhythm.repository.WeeklyReleasesRepository;
import com.ubeydekara.rhythm.repository.WeeklyTracksRepository;
import com.ubeydekara.rhythm.response.Artist;
import com.ubeydekara.rhythm.response.Track;
import com.ubeydekara.rhythm.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartUtils {
    private final WeeklyTracksRepository weeklyTracksRepository;
    private final WeeklyAlbumsRepository weeklyAlbumsRepository;
    private final WeeklyArtistsRepository weeklyArtistsRepository;
    private final WeeklyReleasesRepository weeklyReleasesRepository;

    private final SpotifyService spotifyService;
    private final ModelMapper modelMapper;

    public ChartResponse updateCharts(ChartData chartData) {
        ChartResponse chartResponse = new ChartResponse();

        weeklyTracksRepository.deleteAll();
        weeklyAlbumsRepository.deleteAll();
        weeklyArtistsRepository.deleteAll();

        for (int i = 0; i < chartData.getChartEntryViewResponses().size(); i++) {

            // import weekly tracks
            if (ChartType.TRACKS.getIndex() == i) {
                ChartData.ChartEntryViewResponses chartEntryViewResponses =
                        chartData.getChartEntryViewResponses().get(i);

                List<WeeklyTrack> weeklyTracks = prepareWeeklyTracks(chartEntryViewResponses);
                chartResponse.setWeeklyTracks(weeklyTracksRepository.saveAll(weeklyTracks));
            }

            // import weekly albums
            else if (ChartType.ALBUMS.getIndex() == i) {
                ChartData.ChartEntryViewResponses chartEntryViewResponses =
                        chartData.getChartEntryViewResponses().get(i);

                List<WeeklyAlbum> weeklyAlbums = prepareWeeklyAlbums(chartEntryViewResponses);
                chartResponse.setWeeklyAlbums(weeklyAlbumsRepository.saveAll(weeklyAlbums));
            }

            // import weekly artists
            else if (ChartType.ARTISTS.getIndex() == i) {
                ChartData.ChartEntryViewResponses chartEntryViewResponses =
                        chartData.getChartEntryViewResponses().get(i);

                List<WeeklyArtist> weeklyArtists = prepareWeeklyArtists(chartEntryViewResponses);
                chartResponse.setWeeklyArtists(weeklyArtistsRepository.saveAll(weeklyArtists));
            }
        }

        return chartResponse;
    }

    public List<WeeklyReleases> importWeeklyReleases() {
        List<Track> tracks = spotifyService.getPlaylistTracks(SpotifyConstants.newMusicFridayPlaylistId);
        List<WeeklyReleases> weeklyReleases = List.of(modelMapper.map(tracks, WeeklyReleases[].class));

        weeklyReleasesRepository.deleteAll();
        weeklyReleasesRepository.saveAll(weeklyReleases);

        return weeklyReleases;
    }

    private List<WeeklyTrack> prepareWeeklyTracks(ChartData.ChartEntryViewResponses chartEntryViewResponses) {
        List<WeeklyTrack> weeklyTracks = new ArrayList<>();

        for (ChartData.ChartEntryViewResponses.Entry entry : chartEntryViewResponses.getEntries()) {
            List<Artist> artists = new ArrayList<>(entry.getTrackMetadata().getArtists().size());

            for (ChartData.ChartEntryViewResponses.Entry.TrackMetadata.Artist artist : entry.getTrackMetadata().getArtists()) {
                Artist artistRes = Artist.builder()
                        .id(artist.getSpotifyUri())
                        .name(artist.getName())
                        .build();
                artists.add(artistRes);
            }

            WeeklyTrack weeklyTrack = WeeklyTrack.builder()
                    .id(entry.getTrackMetadata().getTrackUri())
                    .name(entry.getTrackMetadata().getTrackName())
                    .image(entry.getTrackMetadata().getDisplayImageUri())
                    .previousRank(entry.getChartEntryData().getPreviousRank())
                    .currentRank(entry.getChartEntryData().getCurrentRank())
                    .artists(artists)
                    .build();

            weeklyTracks.add(weeklyTrack);
        }

        return weeklyTracks;
    }

    private List<WeeklyAlbum> prepareWeeklyAlbums(ChartData.ChartEntryViewResponses chartEntryViewResponses) {
        List<WeeklyAlbum> weeklyAlbums = new ArrayList<>();

        for (ChartData.ChartEntryViewResponses.Entry entry : chartEntryViewResponses.getEntries()) {
            List<Artist> artists = new ArrayList<>(entry.getAlbumMetadata().getArtists().size());

            for (ChartData.ChartEntryViewResponses.Entry.AlbumMetadata.Artist artist : entry.getAlbumMetadata().getArtists()) {
                Artist artistRes = Artist.builder()
                        .id(artist.getSpotifyUri())
                        .name(artist.getName())
                        .build();
                artists.add(artistRes);
            }

            WeeklyAlbum weeklyAlbum = WeeklyAlbum.builder()
                    .id(entry.getAlbumMetadata().getAlbumUri())
                    .name(entry.getAlbumMetadata().getAlbumName())
                    .image(entry.getAlbumMetadata().getDisplayImageUri())
                    .previousRank(entry.getChartEntryData().getPreviousRank())
                    .currentRank(entry.getChartEntryData().getCurrentRank())
                    .artists(artists)
                    .build();

            weeklyAlbums.add(weeklyAlbum);
        }

        return weeklyAlbums;
    }

    private List<WeeklyArtist> prepareWeeklyArtists(ChartData.ChartEntryViewResponses chartEntryViewResponses) {
        List<WeeklyArtist> weeklyArtists = new ArrayList<>();

        for (ChartData.ChartEntryViewResponses.Entry entry : chartEntryViewResponses.getEntries()) {
            WeeklyArtist weeklyArtist = WeeklyArtist.builder()
                    .id(entry.getArtistMetadata().getArtistUri())
                    .name(entry.getArtistMetadata().getArtistName())
                    .image(entry.getArtistMetadata().getDisplayImageUri())
                    .previousRank(entry.getChartEntryData().getPreviousRank())
                    .currentRank(entry.getChartEntryData().getCurrentRank())
                    .build();

            weeklyArtists.add(weeklyArtist);
        }

        return weeklyArtists;
    }
}
