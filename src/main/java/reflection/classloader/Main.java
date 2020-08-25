package reflection.classloader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) throws IOException {
        JarFile jarFile = new JarFile("C:/Users/harde/IdeaProjects/training-laboratory/src/main/java/reflection/classloader/lombok.jar");
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
        }
    }
}
