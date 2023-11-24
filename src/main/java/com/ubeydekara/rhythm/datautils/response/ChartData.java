package com.ubeydekara.rhythm.datautils.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChartData {
    private List<ChartEntryViewResponses> chartEntryViewResponses;

    @Data
    public static class ChartEntryViewResponses {
        private List<Entry> entries;

        @Data
        public static class Entry {
            private AlbumMetadata albumMetadata;
            private TrackMetadata trackMetadata;
            private ArtistMetadata artistMetadata;
            private ChartEntryData chartEntryData;

            @Data
            public static class ChartEntryData {
                private Integer currentRank;
                private Integer previousRank;
            }

            @Data
            public static class AlbumMetadata {
                private String albumName;
                private String albumUri;
                private String displayImageUri;
                private List<Artist> artists;

                public String getAlbumUri() {
                    return albumUri.split(":")[2];
                }

                @Data
                public static class Artist {
                    private String name;
                    private String spotifyUri;

                    public String getSpotifyUri() {
                        return spotifyUri.split(":")[2];
                    }
                }
            }

            @Data
            public static class TrackMetadata {
                private String trackName;
                private String trackUri;
                private String displayImageUri;
                private List<Artist> artists;

                public String getTrackUri() {
                    return trackUri.split(":")[2];
                }

                @Data
                public static class Artist {
                    private String name;
                    private String spotifyUri;

                    public String getSpotifyUri() {
                        return spotifyUri.split(":")[2];
                    }
                }
            }

            @Data
            public static class ArtistMetadata {
                private String artistName;
                private String artistUri;
                private String displayImageUri;

                public String getArtistUri() {
                    return artistUri.split(":")[2];
                }
            }
        }
    }
}
