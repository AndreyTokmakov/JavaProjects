package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

class DateLongFormatTypeAdapter extends TypeAdapter<Timestamp>
{
    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        if(value != null) out.value(value.getTime());
        else out.nullValue();
    }

    @Override
    public Timestamp read(JsonReader in) throws IOException {
        return new Timestamp(in.nextLong());
    }
}



public class GsonSerialisationTests
{
    private static final Gson gson;

    static
    {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();
    }

    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        return gson.fromJson(gsonString, new TypeToken<Map<String, T>>(){}.getType());
    }

    public static void main(String[] args)
    {
        final String cmdString = "{\"action\":\"recovery\",\"timestamp\":\"1727433106\"," +
                "\"pair\":\"BTC/USDT\", \"status\":\"BTC/READY\"}";


        Map<String, Object> dict = GsonToMaps(cmdString);
        //System.out.println(dict);

        for (var entry: dict.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
