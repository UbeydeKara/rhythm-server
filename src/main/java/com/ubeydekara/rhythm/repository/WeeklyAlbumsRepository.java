package com.ubeydekara.rhythm.repository;

import com.ubeydekara.rhythm.model.WeeklyAlbum;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeeklyAlbumsRepository extends MongoRepository<WeeklyAlbum, String> {
}
