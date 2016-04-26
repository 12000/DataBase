package app;

import java.util.Vector;

public class User {
	private int id;
	private String name;
	private String surname;
	private String docNum;
	private String docType;
	
	private Vector<Vector<Object>> orders;
	
	public User( int id, String name, String surname, String docNum, String docType){
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.docNum = docNum;
		this.docType = docType;
	}
	
	public void SetOrders( Vector<Vector<Object>> new_orders){
		this.orders.clear();
		this.orders = new_orders;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public String getDocNum(){
		return this.docNum;
	}
	
	public String getDocType(){
		return this.docType;
	}
	
	public int getId(){
		return this.id;
	}

}
