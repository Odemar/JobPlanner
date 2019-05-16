package Classes;

/**
 * Class as a template for a user, with rights, username, password, name, email address and phone number.
 */
public class User {

    /**
     * Type controls what the User can/cannot see.
     * 0 = admin, 1 = client, 2 = staff
     */
    int type;
    /**
     * Username
     */
    String username;
    /**
     * Password
     */
    String password;
    /**
     * Name
     */
    String name;
    /**
     * Email
     */
    String mail;
    /**
     * Cell phone number
     */
    int cell;

    /**
     * Constructor for User.
     *
     * @param type     type of user
     * @param username username (must be unique)
     * @param password password
     * @param name     official name
     * @param mail     email address
     * @param cell     cell phone number
     */
    public User(int type, String username, String password, String name, String mail, int cell) {
        this.type = type;
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setMail(mail);
        this.setCell(cell);
    }

    /**
     * Makes a string of the users details.
     *
     * @return String of the Users details, each detail separated by spaces
     */
    @Override
    public String toString() {
        String values = type + " " + username + " " + password + " " + name + " " + mail + " " + cell;
        return values;
    }

    /**
     * Returns the type of user.
     *
     * @return Type of user
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the username.
     *
     * @param username New username of the User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username.
     *
     * @return Username of the User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the password.
     *
     * @param password New password for the User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password.
     *
     * @return Password of the User
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the name.
     *
     * @param name New name for the User
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name.
     *
     * @return Name of the User
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes the mail variable.
     *
     * @param mail New email address
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Changes the cell phone number.
     *
     * @param cell New cell phone number
     */
    public void setCell(int cell) {
        this.cell = cell;
    }

    /**
     * Returns the mail variable.
     *
     * @return Email address
     */
    public String getMail() {
        return mail;
    }

    /**
     * Returns the phone number.
     *
     * @return Phone number
     */
    public int getCell() {
        return cell;
    }
}
