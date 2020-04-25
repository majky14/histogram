package sk.vismek.histogram.helpers;

import java.io.File;
import java.util.Objects;

public class ResourceLoader {
    public static File getResourceFile(String filename) {
        return new File(Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource(filename)).getFile());
    }
}
