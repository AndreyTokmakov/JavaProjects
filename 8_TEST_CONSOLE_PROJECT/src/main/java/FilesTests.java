import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FilesTests
{
    public static void main(String[] args) throws IOException {

        String data =  Files.readString(Paths.get("/home/andtokm/Temp/deribit_sub_account.json"));
        System.out.println(data);
    }
}
