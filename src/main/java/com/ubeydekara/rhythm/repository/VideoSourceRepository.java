package com.ubeydekara.rhythm.repository;

import com.ubeydekara.rhythm.model.VideoSource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoSourceRepository extends MongoRepository<VideoSource, String> {
}
