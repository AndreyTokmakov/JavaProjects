package message_parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
class CommandMessage<T> {
    private String action;
    private String pair;
    private Timestamp timestamp;
    private T data;
}

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


class GsonUtil
{
    private static final Gson gson;

    static
    {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
                .create();
    }

    public static String GsonToString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T GsonToBean(String gsonString, Class<T> tClass) {
        return gson.fromJson(gsonString, tClass);
    }

    public static <T> T GsonToBean(String gsonString, Type type) {
        return gson.fromJson(gsonString, type);
    }

    public static <T> List<T> GsonToList(String gsonString) {
        return gson.fromJson(gsonString, new TypeToken<List<T>>(){}.getType());
    }

    public static <T> List<T> GsonToList(String gsonString, Type typeToken ) {
        return gson.fromJson(gsonString,typeToken);
    }

    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        return gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>(){}.getType());
    }

    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        return gson.fromJson(gsonString, new TypeToken<Map<String, T>>(){}.getType());
    }
}


public class CommandParser
{
    public static void main(String[] args)
    {
        final String cmdString = "{\"action\":\"recovery\",\"timestamp\":\"1727433106\"," +
                "\"pair\":\"BTC/USDT\", \"status\":\"BTC/READY\"}";
        final CommandMessage<?> commandMessage = GsonUtil.GsonToBean(cmdString, CommandMessage.class);

        System.out.println(commandMessage);
        System.out.println("Pair: " + commandMessage.getPair());
        System.out.println("Timestamp: " + commandMessage.getTimestamp().getTime());
    }
}
