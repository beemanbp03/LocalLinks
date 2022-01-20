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

    @Column(name = "place_id")
    private String place_id;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "rating")
    private Double rating;

    /**
     * Instantiates a new Favorite
     */
    public Favorite() {
    }

    public Favorite(String place_id, String lat, String lng, Double rating, User user) {
        this.place_id = place_id;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.user = user;
    }

    /**
     * GET for ID instance variable
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * SET for ID instance variable
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GET for USER instance variable
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * SET for USER instance variable
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * GET for the user instance object's id instance variable
     *
     * @return id of user instance object
     */
    public int getUserId() {
        return user.getId();
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * GET for RATING instance variable
     *
     * @return
     */
    public Double getRating() {
        return rating;
    }

    /**
     * SET for RATING instance variable
     *
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
                place_id == favorite.place_id &&
                lat == favorite.lat &&
                lng == favorite.lng &&
                Objects.equals(user, favorite.user) &&
                Objects.equals(rating, favorite.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, place_id, lat, lng, rating);
    }

    @Override
    public String toString(){
        return
                "Favorite{" +
                        "place_id='" + place_id + '\'' +
                        "lat='" + lat + '\'' +
                        "lng='" + lng + '\'' +
                        "rating='" + rating + '\'' +
                        "}";
    }

}