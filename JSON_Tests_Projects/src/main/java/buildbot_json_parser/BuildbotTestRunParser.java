package buildbot_json_parser;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BuildbotTestRunParser implements IRequestParser {
	/** Worker name json parameter name : **/
    final static String workerNameParam = "worker_name";
    /** Start time json parameter name : **/
    final static String startTimeParam  = "start_time";
    /** End time json parameter name : **/
    final static String endTimeParam    = "end_time";
    /** In nightly Json parameter name : **/
    final static String isNightlyParam  = "is_nightly";
    /** Test result list json parameter name : **/
    final static String testRunParam    = "tests";

    /** BuildbotTestRunParser class constructor : **/
    public BuildbotTestRunParser() {
    	
    }

	@Override
	public String parseRequest(final String requesst) throws MessageFormatExeption {
		JSONObject jsonRoot = null;
		try {
			jsonRoot = (JSONObject) (new JSONParser().parse(requesst));
		} catch (ParseException exc) {
			throw new MessageFormatExeption(exc);
		}
		
		String workername = this.getWorkerName(jsonRoot);
		String startTs = this.getStartTimestamp(jsonRoot);
		String endTs = this.getEndTimestamp(jsonRoot);
		boolean isNightly = this.getIsNightly(jsonRoot);
		
		System.out.println(workername);
		System.out.println(startTs);
		System.out.println(endTs);
		System.out.println(isNightly);
		
		if (false == jsonRoot.containsKey(testRunParam)) {
			System.out.println("Key " + testRunParam + " do not exist");
		} else {
			JSONObject tests = (JSONObject) jsonRoot.get(testRunParam);
			for (Object jsonObj : tests.entrySet()) {
				@SuppressWarnings("rawtypes")
	    		Map.Entry test = (Map.Entry) jsonObj;
				String testName = (String) test.getKey();
				JSONObject testsResult = (JSONObject) test.getValue();
				System.out.println(testName + "  =  " + testsResult);
			}
		}
		
		return null;
	}
	
	protected String getStringParamValue(final JSONObject jsonRoot, 
										 final String paramName) throws MessageFormatExeption {
	    if (!jsonRoot.containsKey(paramName)) {
	    	throw new MessageFormatExeption("Failed to find parameter " + paramName);
	    }
		try {
			return (String)jsonRoot.get(paramName);
		} catch (ClassCastException castExc) {
			throw new MessageFormatExeption("Failed to cast parameter '" + paramName  + "' value to String type parameter");
		}
	}
	
	protected Boolean getBooleanParamValue(final JSONObject jsonRoot, 
			 							   final String paramName) throws MessageFormatExeption {
		if (!jsonRoot.containsKey(paramName)) {
			throw new MessageFormatExeption("Failed to find parameter " + paramName);
		}
		try {
			return (Boolean)jsonRoot.get(paramName);
		} catch (ClassCastException castExc) {
			throw new MessageFormatExeption("Failed to cast parameter '" + paramName  + "' value to Boolen type parameter");
		}
	}
	
	protected String getWorkerName(final JSONObject jsonRoot) throws MessageFormatExeption {
	    return getStringParamValue(jsonRoot, workerNameParam);
	}
	
	protected String getStartTimestamp(final JSONObject jsonRoot) throws MessageFormatExeption {
	    return getStringParamValue(jsonRoot, startTimeParam);
	}
	
	protected String getEndTimestamp(final JSONObject jsonRoot) throws MessageFormatExeption {
	    return getStringParamValue(jsonRoot, endTimeParam);
	}
	
	protected Boolean getIsNightly(final JSONObject jsonRoot) throws MessageFormatExeption {
	    return getBooleanParamValue(jsonRoot, isNightlyParam);
	}	
}
