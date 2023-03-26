package Observer;

class Event {
	/** **/
	protected int id = 0;
	
	/** **/
	protected String uuid = "";

	/** **/
	public Event() {
	}

	/**
	 * @param id
	 * @param uuid
	 */
	public Event(int id, String uuid) {
		this.id = id;
		this.uuid = uuid;
	}

	/**  @return the id. **/
	public int getId() {
		return id;
	}

	/**  @param id the id to set. **/
	public void setId(int id) {
		this.id = id;
	}

	/** @return the uuid. **/
	public String getUuid() {
		return uuid;
	}

	/** @param uuis the uuis to set. **/
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	/** Overrides equals() Object method:  **/
	@Override 
	public boolean equals(Object obj) {
		final Event toCompareWith = (Event)obj;
		if (this.id != toCompareWith.id)
			return false;
		return 0 == this.uuid.compareTo(toCompareWith.uuid);
	}
	
	/** Overrides toString() Object method:  **/
	@Override 
	public String toString() {
		return "{ id : "  + this.id + ", uuid: " + this.uuid + "}";
	}
}

/** EventProducer class. **/
class SMTPServive {
	
	public void handleEvent(final Event event) {
		System.out.println("SMTPServive: Handling event. " + event);
	}
}

/** EventProducer class. **/
class EventProducer extends Observable {
	
	/** Produce the event.**/
	public void produce(final Event event) {
		this.notifyObservers(event);
	}
	
	
}


/** MailIObserver class. **/
class MailObserver implements IObserver {
	
	private SMTPServive smtpServive = null;
	
	public MailObserver(final SMTPServive smtpServive) {
		this.smtpServive = smtpServive;
	}
	
	@Override
	public void update(IObservable observable, Object arguments) {
		EventProducer e = (EventProducer)arguments;
		Event event = (Event)arguments;
		smtpServive.handleEvent(event);
	}

}


public class ObserverTests {

	/** @param args **/
	public static void main(String[] args) {
		SMTPServive stvSmtp = new SMTPServive();
		EventProducer producer = new EventProducer();
		MailObserver observer = new MailObserver(stvSmtp);
		
		producer.addObserver(observer);
		producer.produce(new Event(1, "ed5d3b9f-c400-427c-afd0-560179934c5d"));
	}
}
