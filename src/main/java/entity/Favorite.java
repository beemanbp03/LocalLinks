package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class contains all relevent information for a row in the 'favorites' table
 */

@Entity(name = "Favorites")
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private int phone;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "zip_code")
    private int zip_code;

    @Column(name = "state")
    private String state;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "rating")
    private Double rating;

    /**
     * GET for ID instance variable
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * SET for ID instance variable
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GET for USER_ID instance variable
     * @return user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * SET for USER_ID instance variable
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * GET for NAME instance variable
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * SET for NAME instance variable
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GET for PHONE instance variable
     * @return phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * SET for PHONE instance variable
     * @param phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * GET for ADDRESS 1 instance variable
     * @return address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * SET for address1 instance variable
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * GET for ADDRESS2 instance variable
     * @return address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * SET for ADDRESS2 instance variable
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * GET for ZIP_CODE instance variable
     * @return zip_code
     */
    public int getZip_code() {
        return zip_code;
    }

    /**
     * SET for ZIP_CODE instance variable
     * @param zip_code
     */
    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    /**
     * GET for STATE instance variable
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * SET for STATE instance variable
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * GET for DISTANCE instance variable
     * @return distance
     */
    public java.lang.Double getDistance() {
        return distance;
    }

    /**
     * SET for DISTANCE instance variable
     * @param distance
     */
    public void setDistance(java.lang.Double distance) {
        this.distance = distance;
    }

    /**
     * GET for RATING instance variable
     * @return
     */
    public Double getRating() {
        return rating;
    }

    /**
     * SET for RATING instance variable
     * @param rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }
}
