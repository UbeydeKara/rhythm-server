package com.ubeydekara.rhythm.response.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotifyToken {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private LocalDateTime expires_at;

    @Override
    public String toString() {
        return this.token_type + " " + this.access_token;
    }
}
