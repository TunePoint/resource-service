package ua.tunepoint.resource.service.converter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.data.entity.AudioResource;
import ua.tunepoint.resource.utils.StorageUtils;

import java.io.File;
import java.time.LocalDateTime;

import static ua.tunepoint.resource.utils.FileUtils.md5Hash;

@Component
public class AudioConverter implements ResourceConverter<AudioResource> {

    @Override
    public AudioResource convert(MultipartFile multipart, File persisted) {

        var extension = FilenameUtils.getExtension(multipart.getOriginalFilename());

        return AudioResource.builder()
                .created(LocalDateTime.now())
                .md5Hash(md5Hash(persisted))
                .size(persisted.length())
//                .duration(getDuration(persisted))
                .storageKey(StorageUtils.keyFor(extension))
                .extension(extension)
                .originalName(multipart.getOriginalFilename())
                .build();
    }
}
