package com.ubeydekara.rhythm.repository;

import com.ubeydekara.rhythm.model.WeeklyTrack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeeklyTracksRepository extends MongoRepository<WeeklyTrack, String> {
}
