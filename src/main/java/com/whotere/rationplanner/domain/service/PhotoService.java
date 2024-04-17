package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Photo savePhoto(MultipartFile multipartFile);

    Resource loadPhoto(String filename);
}
