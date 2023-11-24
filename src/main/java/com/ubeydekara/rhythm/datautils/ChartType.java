package com.ubeydekara.rhythm.datautils;

import lombok.Getter;

@Getter
public enum ChartType {
    TRACKS(0),
    ALBUMS(1),
    ARTISTS(2);

    private final Integer index;

    ChartType(Integer index) {
        this.index = index;
    }
}
