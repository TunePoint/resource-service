package ua.tunepoint.resource.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

@UtilityClass
@Slf4j
public class AudioUtils {

    @Nullable
    public static Integer getDuration(File audioFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioInputStream.getFormat();
            long audioFileLength = audioFile.length();
            int frameSize = format.getFrameSize();
            float frameRate = format.getFrameRate();

            return Math.round(audioFileLength / (frameSize * frameRate));
        } catch (Exception ex) {
            log.warn("Error occurred while getting audio file duration", ex);
            return null;
        }
    }
}
