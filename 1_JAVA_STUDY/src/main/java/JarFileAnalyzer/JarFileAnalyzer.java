package JarFileAnalyzer;

import java.io.IOException;
import java.util.jar.JarFile;

public class JarFileAnalyzer {
    public static void main(String[] args) throws IOException {
        final String filePath = "/home/andtokm/DiskS/Yandex/qa-java-automation/trainer/build/libs/trainer-release-1.6.71.jar";
        try (JarFile jarFile = new JarFile(filePath)) {
            System.out.println(jarFile);
            // System.out.println(jarFile.entries());
            // System.out.println(jarFile.getVersion());

            // System.out.println(jarFile.getManifest().getEntries());
            // System.out.println(jarFile.getManifest().getAttributes("version"));
            System.out.println(jarFile.getManifest().getMainAttributes().getValue("Trainer-Version"));
        }
    }

    public boolean isAdult(int age) {
        return age > 18;
    }
}
