package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.util.Arrays;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class CsvExperiments
{
    private final static String csvData = """
            "Date","Asset","Binance-Account1","Okx-Account1"
            "2024-01-31","Ethereum","","20 (20000)"
            "2024-01-01","Bitcoin","10 (10000)",""
            """;

    public static void main(String[] args) throws IOException, CsvValidationException
    {
        List<String[]> table;
        try (CSVReader csvReader = new CSVReader(new StringReader(csvData)))
        {
            table = csvReader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }



        System.out.println(table.size());
        System.out.println(Arrays.asList(table.getFirst()));
        System.out.println(Arrays.asList(table.get(1)));
        System.out.println(Arrays.asList(table.get(2)));



        String[] header = {"Date", "Asset", "Binance-Account1", "Okx-Account1"};

        System.out.println(Arrays.equals(header, table.getFirst()));
    }
}
