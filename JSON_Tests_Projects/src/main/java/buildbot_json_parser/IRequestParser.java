package buildbot_json_parser;

public interface IRequestParser {
	/** Parse request : 
	 * @throws MessageFormatExeption **/
	public String parseRequest(final String requesst) throws MessageFormatExeption;
}
