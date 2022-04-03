package ua.tunepoint.resource.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.tunepoint.resource.data.entity.Resource;

import java.io.File;
import java.time.LocalDateTime;

import static ua.tunepoint.resource.utils.FileUtils.md5Hash;

@UtilityClass
public class MultipartFileUtils {

    public static Resource extractResource(MultipartFile multipartFile, File persistedFile) {

        return Resource.builder()
                .created(LocalDateTime.now())
                .originalName(multipartFile.getOriginalFilename())
                .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                .size(multipartFile.getSize())
                .md5Hash(md5Hash(persistedFile))
                .build();
    }
}
