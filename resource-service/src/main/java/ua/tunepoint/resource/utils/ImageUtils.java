package ua.tunepoint.resource.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ua.tunepoint.resource.data.entity.ImageResource;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
@UtilityClass
public class ImageUtils {

    @Nullable
    public static ImageResource.Resolution getResolution(File imageFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            return new ImageResource.Resolution(bufferedImage.getWidth(), bufferedImage.getHeight());
        } catch (Exception ex) {
            log.warn("Error occurred while getting image resolution", ex);
            return null;
        }
    }
}
