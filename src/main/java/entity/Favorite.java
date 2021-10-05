package entity;

import org.hibernate.annotations.GenericGenerator;
import entity.User;
import javax.persistence.*;
import java.util.Objects;

/**
 * This class contains all relevent information for a row in the 'favorites' table
 */

@Entity(name = "Favorite")
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private long phone;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "zip_code")
    private int zip_code;

    @Column(name = "state")
    private String state;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "rating")
    private Double rating;

    /**
     * Instantiates a new Favorite
     */
    public Favorite() {
    }

    public Favorite(String name, long phone, String address1, int zip_code, String state, Double distance, Double rating, User user) {
        this.name = name;
        this.phone = phone;
        this.address1 = address1;
        this.zip_code = zip_code;
        this.state = state;
        this.distance = distance;
        this.rating = rating;
        this.user = user;
    }

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
     * GET for USER instance variable
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * SET for USER instance variable
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * GET for the user instance object's id instance variable
     * @return id of user instance object
     */
    public int getUserId(){ return user.getId();}

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
    public long getPhone() {
        return phone;
    }

    /**
     * SET for PHONE instance variable
     * @param phone
     */
    public void setPhone(long phone) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return id == favorite.id &&
                phone == favorite.phone &&
                zip_code == favorite.zip_code &&
                Objects.equals(user, favorite.user) &&
                Objects.equals(name, favorite.name) &&
                Objects.equals(address1, favorite.address1) &&
                Objects.equals(state, favorite.state) &&
                Objects.equals(distance, favorite.distance) &&
                Objects.equals(rating, favorite.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, phone, address1, zip_code, state, distance, rating);
    }
}
