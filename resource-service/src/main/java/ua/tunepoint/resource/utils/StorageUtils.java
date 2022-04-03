package ua.tunepoint.resource.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class StorageUtils {


    public static String keyFor(String extension) {
        return UUID.randomUUID() + "."  + extension;
    }
}
