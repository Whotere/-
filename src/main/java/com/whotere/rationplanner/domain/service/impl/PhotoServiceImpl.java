package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.data.model.Photo;
import com.whotere.rationplanner.data.repository.PhotoRepository;
import com.whotere.rationplanner.domain.exception.BadRequestException;
import com.whotere.rationplanner.domain.exception.EntityNotFoundException;
import com.whotere.rationplanner.domain.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final Set<String> PHOTO_CONTENT_TYPES = new HashSet<>();

    {
        PHOTO_CONTENT_TYPES.add("image/jpeg");
        PHOTO_CONTENT_TYPES.add("image/png");
    }

    private final PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(MultipartFile multipartFile) {

        if(!PHOTO_CONTENT_TYPES.contains(multipartFile.getContentType())) {
            throw new BadRequestException("Фотография должна иметь расширение jpg, jpeg, png, jpe или jfif");
        }

        Photo photo = new Photo();
        try {
            photo.setContent(multipartFile.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("Фотография, которую вы выбрали, битая");
        }

        photoRepository.saveAndFlush(photo);

        return photo;
    }

    @Override
    @Transactional
    public Resource loadPhoto(String id) {

        byte[] resource = photoRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Фотография с id [%s] не найдена", id))
                )
                .getContent();

        return new ByteArrayResource(resource);
    }
}
