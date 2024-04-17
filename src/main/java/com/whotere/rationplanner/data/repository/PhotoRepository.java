package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, String> {}
