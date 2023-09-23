import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

// Create:
// keytool -genkey -alias mydomain -keyalg RSA -keystore /home/andtokm/Temp/KeyStore.jks -keysize 2048

// TODO: https://www.baeldung.com/java-keystore
// TODO: https://habr.com/ru/articles/445786/

public class CreateKeyStore
{
    private static final String JAVA_KEYSTORE_FOLDER = "/home/andtokm/Temp/Java/";

    public static void createKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        final String password = "12345";
        ks.load(null, password.toCharArray());
        // Store away the keystore.
        try (final FileOutputStream outStream = new FileOutputStream(JAVA_KEYSTORE_FOLDER + "testStore.jks")) {
            ks.store(outStream, password.toCharArray());
        }
    }

    public static void main(String[] args)
            throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException
    {
        createKey();
    }
}
