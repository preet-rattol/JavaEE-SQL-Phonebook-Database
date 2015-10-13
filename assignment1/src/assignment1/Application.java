package assignment1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Application {

	public static void main(String []args){
		runMenu();
	}

	public static void runMenu(){
		Scanner s = new Scanner(System.in);

		try {
			DB_Access db = new DB_Access();
			Person p;
			
			while(true){
				System.out.println();
				System.out.println("Choose one of the following option: \n"+
						"1. Add New Contact\n" +
						"2. Display All Contacts\n" + 
						"3. Search Contact\n" + 
						"4. Delete Contact\n" +
						"5. Edit Contact"
						);
				int choice = s.nextInt();
				
				switch(choice){

				case 1:
					// inserting contact
					try{
						db.insertContact(getContactInfo());
						p = db.moveToLast();
						if(p != null){
							displayContact(p);
							System.out.println("Saved Successfully");
						}
						else System.out.println("phonebook is empty");
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid number !");
					}
					break;

				case 2:
					//displaying all contacts
					ArrayList<Person> list = db.showAllContacts();
					System.out.println("Displaying all contacts");
					System.out.println("ID              Name          Contact Number");
					displayAllContacts(list);
					break;

				case 3:
					//searching for one contact
					Scanner s1 = new Scanner(System.in);
					System.out.println("Enter contact name:");
					String name = s1.nextLine();
					p = db.getContact(name);
					if(p != null){
						// contact exists and display it
						displayContact(p);
					}
					else{
						// contact does not exist
						System.out.println("Contact " + name +" not found !");
					} 
					break;
				case 4:
					//deleting contact
				    list = db.showAllContacts();
					System.out.println("Displaying all contacts");
					System.out.println("ID              Name          Contact Number");
					displayAllContacts(list);
					
					System.out.println("Enter ID of contact to delete :");
					int id = s.nextInt();
					db.deleteContact(id);
					break;
				case 5:
					//Edit Contact
				    list = db.showAllContacts();
					System.out.println("Displaying all contacts");
					System.out.println("ID              Name          Contact Number");
					displayAllContacts(list);
					System.out.println("Enter ID of contact to edit :");
					int id1 = s.nextInt();
					s.nextLine();
					System.out.println("Enter new name : ");
					String name1 = s.nextLine();
					System.out.println("Enter new contact :");
					long contact1 = s.nextLong();
					db.editContact(id1, name1, contact1);
					break;
				default:
					System.out.println("Wrong Option !");
				}

			}
		}
		catch (ClassNotFoundException | SQLException | InputMismatchException e) {
			System.out.println("Please type an integer value !");
//			e.printStackTrace();
			runMenu();

		}
	}

	// display one contact
	public static void displayContact(Person p)	{
		System.out.println(p.getContactId() +"		"+ p.getName() +"		"+ p.getContact());
	}

	//displaying the whole phonebook
	public static void displayAllContacts(ArrayList<Person> list) {
		for (Person p : list) {
			displayContact(p);
		}
	}

	public static Person getContactInfo() {

		Scanner s = new Scanner(System.in);
		Person p;

		System.out.println("Enter name:");
		String name = s.nextLine();

		System.out.println("Enter contact no. :");
		long contact = s.nextLong();

		p = new Person( name, contact);
		return p;

	}





}
