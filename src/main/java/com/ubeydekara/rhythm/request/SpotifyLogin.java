package com.ubeydekara.rhythm.request;

import com.ubeydekara.rhythm.constant.SpotifyConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpotifyLogin {
    private final String grantType = SpotifyConstants.grantType;
    private final String clientId = SpotifyConstants.clientId;
    private final String clientSecret = SpotifyConstants.clientSecret;
}
