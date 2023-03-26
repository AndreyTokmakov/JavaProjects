package ObjectOrientedProgramming;

public class MutableExample {
	private String address;

	public MutableExample(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static void main(String[] args) {
		MutableExample obj = new MutableExample("first address");
		System.out.println(obj.getAddress());
		
		obj.setAddress("Updated address");
		System.out.println(obj.getAddress());
	}
}
