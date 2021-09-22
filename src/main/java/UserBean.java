package src.main.UserBean;


/**
 * The User class contains all relevent information for a single field in the User table
 *
 */
public class User(int userID, String userName, String password, String email, String firstName, String lastName, int zipCode) {
    int userID;
    String userName;
    String password;
    String email;
    String firstName;
    String lastName;
    int zipCode;

    /**
     * Class Constructor for User
     * @param userID int
     * @param userName String
     * @param password String
     * @param firstName String
     * @param lastName String
     * @param zipCode int
     */
    private User(int userID, String userName, String password, String firstName, String lastName, int zipCode) {
        this.userID = 0;
        this.userName = "name not chosen...";
        this.userEmail = "email not chosen...";
        this.firstName = "first name not chosen...";
        this.lastName = "last name not chosen...";
        this.zipCode = 0000;
    }

    /**
     * GETTER for userID
     * @return userID int value
     */
    public int getUserID() {
        return userID;
    }

    /**
     * SETTER for userID instance variable
     * @param userID int value
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * GETTER for userName instance variable
     * @return userName string value
     */
    public String getUserName() {
        return userName;
    }

    /**
     * SETTER for userName instance variable
     * @param userName string value
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * GETTER for password instance variable
     * @return password string value
     */
    public String getPassword() {
        return password;
    }

    /**
     * SETTER for password instance variable
     * @param password string value
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * GETTER for email instance variable
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * SETTER for email instance variable
     * @param email string value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * GETTER for firstName instance variable
     * @return firstName string value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * SETTER for firstName instance variable
     * @param firstName string value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * GETTER for lastName instance variable
     * @return lastName string value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * SETTER for lastName instance variable
     * @param lastName string value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * GETTER for zipCode instance variable
     * @return zipCode int value
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * SETTER for zipCode instance variable
     * @param zipCode int value
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}