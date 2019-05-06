
public class User {
	int type;
	String username;
	String password;
	String name;
	
	 // type for Admin is 0, Client is 1, staff is 2
	public void setUser(int type,String username,String password,String name) {
		this.type = type;
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}
	// Get and Set methods
	public int getType() {
		return this.type;
	}
	
 	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getUsername() 
	{
		return this.username;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getPassword() 
	{
		return this.password;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public String getUserString() {
		String values = Integer.toString(type) + " " + username + " " + password + " " + name;
		return values;
	}
	
}
