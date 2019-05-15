package Classes;

/**
 * Class as a template for a user, with rights, username, password and name.
 */
public class User {

    // type controls what the user can/cannot see; 0 = admin, 1 = client, 2 = staff
    int type;
    // username
    String username;
    // password
    String password;
    // name
    String name;
    // email
    String mail;
    // cell phone number
    int cell;


    public User(int type,String username,String password,String name, String mail, int cell) {
        this.type = type;
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setMail(mail);
        this.setCell(cell);
    }

    /**
     * Returns the type of user.
     * @return
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the username.
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Returns the username.
     * @return
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Sets the password.
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the password.
     * @return
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Sets the name.
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Makes a string of the users detaails.
     * @return
     */
    @Override
    public String toString() {
        String values = type + " " + username  + " "  + password +  " "  + name + " " + mail + " " + cell;
        return values;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getMail() {
        return mail;
    }

    public int getCell() {
        return cell;
    }
}
