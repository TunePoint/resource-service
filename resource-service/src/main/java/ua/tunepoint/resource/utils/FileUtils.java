package ua.tunepoint.resource.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@Slf4j
@UtilityClass
public class FileUtils {

    private static final String TMP_PREFIX = "tunepoint";
    private static final String TMP_SUFFIX = ".tmp";

    public static File persistTemporary(MultipartFile multipartFile) {
        try {
            var tempPath = Files.createTempFile(TMP_PREFIX, "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()));

            try (OutputStream os = Files.newOutputStream(tempPath)) {
                os.write(multipartFile.getBytes());
            }

            return tempPath.toFile();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String md5Hash(File file) {
        try (InputStream is = Files.newInputStream(file.toPath())) {
            return DigestUtils.md5Hex(is.readAllBytes());
        } catch (Exception ex) {
            log.warn("Unable to hash file", ex);
            return null;
        }
    }
}
