package buildbot_json_parser;

public class ProjectMain {

	final static String jsonStrin = "{\"worker_name\": \"WorkerTesterWin7x64\", \"start_time\": \"2019-06-18 16:37:04\","
		+ " \"end_time\": \"2019-06-18 16:37:11\", \"is_nightly\": false, \"tests\": {\"chromedriver_unittests\":"
		+ " {\"passed\": 357, \"failed\": 0, \"skipped\": 0}, \"elevation_service_unittests\": {\"passed\": 5, \"failed\": 0, \"skipped\": 0},"
		+ " \"vr_common_unittests\": {\"passed\": 224, \"failed\": 0, \"skipped\": 0}, "
		+ "\"url_unittests\": {\"passed\": 111, \"failed\": 0, \"skipped\": 0}}}";
	
	
	public static void main(String[] args) throws MessageFormatExeption {
		IRequestParser parser = new BuildbotTestRunParser();
		String result = parser.parseRequest(jsonStrin);

	}
}
