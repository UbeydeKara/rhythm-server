package com.ubeydekara.rhythm.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotifyToken {
    private String access_token;
    private String token_type;
    private Integer expires_in;

    @Override
    public String toString() {
        return this.token_type + " " + this.access_token;
    }
}
