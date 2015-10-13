package assignment1;

public class Person {
	private int contactId;
	private String name;
	private long contact;
	
	public Person() {
		super();
	}
	
	public Person(int contactId, String name, long contact) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.contact = contact;
	}
	
	public Person(String name, long contact){
		super();
		this.name = name;
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	
}
