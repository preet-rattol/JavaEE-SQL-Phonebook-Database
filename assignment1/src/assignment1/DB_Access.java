package assignment1;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;



public class DB_Access {
	private String url = "jdbc:mysql://localhost:3306/test";
	private String driver = "com.mysql.jdbc.Driver";
	private String uname = "root";
	private String upass = "";

	private Statement st;
	private Connection con;
	ResultSet rs;
	String sql="";

	public DB_Access() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		con = DriverManager.getConnection(url, uname, upass);
		st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
	
	public Person moveToFirst() throws SQLException{
		Person p = null;
		if(rs.first()){
			p= new Person( rs.getInt(1), rs.getString(2),rs.getLong(3));
		}
		return p;
	}

	public Person moveToLast() throws SQLException{
		Person p = null;
		if(rs.last()){
			p= new Person( rs.getInt(1), rs.getString(2),rs.getLong(3));
		}
		return p;
	}

	public void insertContact(Person p) throws SQLException  {

		sql = "insert into phonebook values(?, ?, ?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, p.getContactId());
		pst.setString(2, p.getName());
		pst.setLong(3, p.getContact());

		try{
			pst.executeUpdate();
		}
		catch(SQLException e){
			System.out.println("Contact was not saved.");
			e.printStackTrace();
		}
		rs = st.executeQuery("select * from phonebook");
	}

	public ArrayList<Person> showAllContacts(){
		ArrayList<Person> list = new ArrayList<Person>();
		String sql = "select contactId, name, contact from phonebook";

		try{
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Person p = new Person(
						rs.getInt(1), 
						rs.getString(2),
						rs.getLong(3)
						);
				list.add(p);
			}
		}
		catch(SQLException e){
			System.out.println("Error displaying all contacts");
			e.printStackTrace();
		}
		return list;

	}

	public Person getContact(String name){
		Person p = null;

		String sql = "select contactId, name, contact from phonebook where name = '" + name + "'";
		try{
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				p = new Person(rs.getInt(1), rs.getString(2),rs.getLong(3));
			}
		}
		catch(SQLException e){
			System.out.println("Unable to find contact");
			e.printStackTrace();
		}
		return p;
	}

	public boolean editContact(int id, String name , long contact){
		String sql = "update phonebook set name = '" + name + "'," +
				"contact = '" + contact +"'" +
				"where contactId = '" + id + "'";
		boolean success = true;

		try{
		 st.executeUpdate(sql);
		
		}
		catch(SQLException e){
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	public boolean deleteContact(int id){
		String sql = "delete from phonebook where contactId = '" + id + "'";
		boolean success = true;

		try{
			int num = st.executeUpdate(sql);
			System.out.println("Contact with ID "+ id + " successfully deleted");
			if(num==0) success = false;
		}
		catch(SQLException e){
			success =false;
			e.printStackTrace();
		}
		return success;
	}
}




