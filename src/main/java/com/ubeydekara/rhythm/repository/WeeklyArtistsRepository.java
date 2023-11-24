package com.ubeydekara.rhythm.repository;

import com.ubeydekara.rhythm.model.WeeklyArtist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeeklyArtistsRepository extends MongoRepository<WeeklyArtist, String> {
}
