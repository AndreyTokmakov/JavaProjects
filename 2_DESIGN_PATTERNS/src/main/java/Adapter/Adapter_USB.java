package Adapter;

interface USB {
	public void connectWithUsbCable();
}

class MemoryCard {
	public void insert() {
		System.out.println("Memory card inserted successfully!");
	}

	public void copyData() {
		System.out.println("Data has been copied to your computer!");
	}
}

class CardReader implements USB {
	private final MemoryCard memoryCard;

	public CardReader(MemoryCard memoryCard) {
		this.memoryCard = memoryCard;
	}

	@Override
	public void connectWithUsbCable() {
		this.memoryCard.insert();
		this.memoryCard.copyData();
	}
}

public class Adapter_USB
{
	public static void main(String[] args)
	{
		USB cardReader = new CardReader(new MemoryCard());
		cardReader.connectWithUsbCable();
	}
}
