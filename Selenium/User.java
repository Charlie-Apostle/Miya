package Selenium;

public class User {
	private Name name;
	private String mail;
	
	public User(Name name, String mail){
		this.name = name;
		this.mail = mail;
	}
	
	@Override
	public String toString(){
		
		return "name:\n " + name.getFirst() + "\n" + name.getLast() + "\nmail:\n" + mail; 
	}
	
	
}
