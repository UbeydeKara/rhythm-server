package com.ubeydekara.rhythm.repository;

import com.ubeydekara.rhythm.model.WeeklyReleases;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeeklyReleasesRepository extends MongoRepository<WeeklyReleases, String> {
}
