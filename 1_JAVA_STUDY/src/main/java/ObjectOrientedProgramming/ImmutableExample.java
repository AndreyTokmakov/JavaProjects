package ObjectOrientedProgramming;

public final class ImmutableExample {

	private String address;

	public ImmutableExample (String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	/*
	public void setAddress(String address) {
		this.address = address;
	}
	*/
	
	public static void main(String[] args) {
		ImmutableExample obj = new ImmutableExample("old address");
		System.out.println(obj.getAddress());
		
		//  obj.setAddress("new address");
	    System.out.println(obj.getAddress());
	}
}
