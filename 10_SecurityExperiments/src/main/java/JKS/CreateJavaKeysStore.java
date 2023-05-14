package JKS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

public class CreateJavaKeysStore
{
    public static KeyStore createKeyStore() throws Exception
    {
        File file = new File("/home/andtokm/DiskS/ProjectsUbuntu/SSL/testkey.jks");
        KeyStore keyStore = KeyStore.getInstance("JKS");
        if (file.exists()) {
            // if exists, load
            keyStore.load(new FileInputStream(file), "123456".toCharArray());
        } else {
            // if not exists, create
            keyStore.load(null, null);
            keyStore.store(new FileOutputStream(file), "123456".toCharArray());
        }
        return keyStore;
    }

    public static void main(String[] args) throws Exception {
        createKeyStore();
    }
}
