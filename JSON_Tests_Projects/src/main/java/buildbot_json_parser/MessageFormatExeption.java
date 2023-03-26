package buildbot_json_parser;

public class MessageFormatExeption extends Exception {
	/**  **/
	private static final long serialVersionUID = 7297960768363527734L;
	
	/** **/
	public MessageFormatExeption(String reason) {
		super(reason);
	}
	
	/** **/
	public MessageFormatExeption(String mgsType, String missingParamName) {
		super("Parsing message type " + mgsType + " : Failed to find parameter " + missingParamName);
	}
	
	/** **/
    public MessageFormatExeption(Throwable cause) {
    	super(cause);
    }
	
	/** **/
    public MessageFormatExeption(String message, Throwable throwable) {
        super(message, throwable);
    }
}
