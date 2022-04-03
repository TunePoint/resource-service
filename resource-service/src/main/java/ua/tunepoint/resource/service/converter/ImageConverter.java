package ua.tunepoint.resource.service.converter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.data.entity.ImageResource;
import ua.tunepoint.resource.utils.StorageUtils;

import java.io.File;
import java.time.LocalDateTime;

import static ua.tunepoint.resource.utils.FileUtils.md5Hash;
import static ua.tunepoint.resource.utils.ImageUtils.getResolution;

@Component
public class ImageConverter implements ResourceConverter<ImageResource> {

    @Override
    public ImageResource convert(MultipartFile multipart, File persisted) {
        var extension = FilenameUtils.getExtension(multipart.getOriginalFilename());

        return ImageResource.builder()
                .created(LocalDateTime.now())
                .md5Hash(md5Hash(persisted))
                .size(persisted.length())
                .resolution(getResolution(persisted))
                .originalName(multipart.getOriginalFilename())
                .storageKey(StorageUtils.keyFor(extension))
                .extension(extension)
                .build();
    }
}
