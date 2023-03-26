package json_parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTester {
	/** **/
	private final String fileName = "C:\\Temp\\test.json";

	/**  **/
	public JsonTester() {
		// TODO Auto-generated constructor stub
	}


	/** WriteToFile : **/
	public void WriteToFile() {
		JSONObject json  = new JSONObject();
		json.put("name", "mkyong.com");
		json.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        json .put("messages", list);

        try (FileWriter file = new FileWriter(this.fileName)) {
            file.write(json .toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(json );
        }

        /** ReadFromFile : **/
        public void ReadFromFile()
        {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(this.fileName));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        	}
       }

	public static void main(String[] args) throws ParseException {
       final String workerNameParam = "worker_name";
       final String startTimeParam  = "start_time";
       final String endTimeParam    = "end_time";
       final String isNightlyParam  = "is_nightly";
       final String testRunParam    = "tests";


       final String jsonStrin = "{\"worker_name\": \"WorkerTesterWin7x64\", \"start_time\": \"2019-06-18 16:37:04\","
                                + " \"end_time\": \"2019-06-18 16:37:11\", \"is_nightly\": false, \"tests\": {\"chromedriver_unittests\":"
                                + " {\"passed\": 357, \"failed\": 0, \"skipped\": 0}, \"elevation_service_unittests\": {\"passed\": 5, \"failed\": 0, \"skipped\": 0},"
                                + " \"vr_common_unittests\": {\"passed\": 224, \"failed\": 0, \"skipped\": 0}, "
                                + "\"url_unittests\": {\"passed\": 111, \"failed\": 0, \"skipped\": 0}}}";

       //System.out.println(jsonStrin);

       JSONObject jsonObject = (JSONObject) (new JSONParser().parse(jsonStrin));
       if (false == jsonObject.containsKey(workerNameParam)) {
		   //throw new MessageFormatExeption("Failed to determine message type");
		   System.out.println("Key " + workerNameParam + " do not exist");
       }

       String workerName = (String)jsonObject.get(workerNameParam);
       System.out.println(workerName);

       if (false == jsonObject.containsKey(startTimeParam)) {
		   //throw new MessageFormatExeption("Failed to determine message type");
		   System.out.println("Key " + startTimeParam + " do not exist");
       } else {
		   String startTime = (String)jsonObject.get(startTimeParam);
		   System.out.println(startTime);
       }

       if (false == jsonObject.containsKey(endTimeParam)) {
		   //throw new MessageFormatExeption("Failed to determine message type");
		   System.out.println("Key " + endTimeParam + " do not exist");
       } else {
		   String endTime = (String)jsonObject.get(endTimeParam);
		   System.out.println(endTime);
       }

       if (false == jsonObject.containsKey(isNightlyParam)) {
		   //throw new MessageFormatExeption("Failed to determine message type");
		   System.out.println("Key " + isNightlyParam + " do not exist");
       } else {
		   boolean isNightly = (Boolean)jsonObject.get(isNightlyParam);
		   System.out.println(isNightly);
       }

       if (false == jsonObject.containsKey(testRunParam)) {
		   //throw new MessageFormatExeption("Failed to determine message type");
		   System.out.println("Key " + testRunParam + " do not exist");
       } else {
    	   JSONObject tests = (JSONObject) jsonObject.get(testRunParam);
    	   for (Object jsonObj : tests.entrySet())
    	   {
    		   @SuppressWarnings("rawtypes")
    		   Map.Entry test = (Map.Entry) jsonObj;
    		   String testName = (String) test.getKey();
    		   JSONObject testsResult = (JSONObject) test.getValue();
    		   
    		   System.out.println(testName + "  =  " + testsResult);
    	   }
       }		
	}
}
