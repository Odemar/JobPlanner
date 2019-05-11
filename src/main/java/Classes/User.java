package Classes;


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
    /**
     *
     * Get and Set methods
     *
     **/
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
        String typeString = new String();
        if (type == 0){
            typeString = "Admin";
        }
        else if(type == 1){
            typeString = "Client";
        }
        else{
            typeString = "Staff";
        }
        String values = "Username = " + username  + ",Rights = " + typeString + ", Full Name = "  + name;
        return values;
    }



}
