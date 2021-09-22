package entity;

import org.hibernate.annotations.GenericGenerator;

/**
 * The User class contains all relevent information for a single field in the User table
 *
 */
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Column(name = "user_id")
    private int userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="zip_code")
    private int zipCode;

    /**
     * Instantiates a new User
     */
    public User() {
    }

    /**
     * Instantiates a new User given params
     * @param userID int
     * @param userName String
     * @param password String
     * @param firstName String
     * @param lastName String
     * @param zipCode int
     */
    private User(int userID, String userName, String password, String email, String firstName, String lastName, int zipCode) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
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

    @Override
    public String toString() {
        return "User {" +
                "userID=" + userID + "\"" +
                ", userName=" + userName + "\"" +
                ", password=" + password + "\"" +
                ", email=" + email + "\"" +
                ", firstName=" + firstName + "\"" +
                ", lastName=" + lastName + "\"" +
                ", zipCode=" + zipCode + "\"" +
                "}";
    }
}