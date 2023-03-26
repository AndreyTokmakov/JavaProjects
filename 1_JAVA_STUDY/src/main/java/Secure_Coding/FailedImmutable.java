package Secure_Coding;

class Text {
	private String text;

	public Text(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (!(obj instanceof Text))
			return false;
		Text rsh = (Text)obj;
		return text.equals(rsh.text);
	}

	@Override
	public String toString() {
		return text.toString();
	}
}

class Person {
	protected Text name;
	protected Text desc;
	
	public Person(Text name, Text desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

	public Text getName() {
		return name;
	}
	
	public void setName(Text name) {
		this.name = name;
	}
	
	public Text getDesc() {
		return desc;
	}
	
	public void setDesc(Text desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, Desc: %s", name, desc);
	}
}

public class FailedImmutable {
	public static void main(String[] args) {
		Text name = new Text("Bob");
		Text desc = new Text("The some test desc");
		
		Person bob = new Person(name, desc);
		System.out.println(bob);
		
		name.setText("Bobby");
		System.out.println(bob);
	}
}
