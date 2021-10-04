package test.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.SessionFactoryProvider;
import persistence.UserDao;
import persistence.FavoriteDao;
import entity.User;
import entity.Favorite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import test.util.Database;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());


    UserDao userDao;
    FavoriteDao favoriteDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
        favoriteDao = new FavoriteDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all users successfully
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = userDao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * Verifies gets user by last name successfully
     */
    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = userDao.getUsersByLastName("Beeman");
        assertEquals(2, users.size());
    }

    /**
     * Verifies a user is returned correctly based on ID search
     */
    @Test
    public void getUsersByIdSuccess() {
        User retrievedUser = userDao.getUserById(1);
        assertEquals("Boulder", retrievedUser.getFirstName());
    }


    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "Williams";
        User userToUpdate = userDao.getUserById(2);
        userToUpdate.setLastName(newLastName);
        userDao.saveOrUpdate(userToUpdate);
        User retrievedUser = userDao.getUserById(2);
        assertEquals(newLastName, retrievedUser.getLastName());
    }

    /**
     * Verifies successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("Packers2021", "Lombardi", "kuhn@madisoncollege.edu", "John", "Kuhn", 53932);
        int id = userDao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = userDao.getUserById(id);
        assertEquals("John", insertedUser.getFirstName());
    }

    /**
     * Verifies Successful delete of user
     */
    @Test
    void deleteSuccess() {
        User user = userDao.getUserById(1);
        Set<Favorite> favorites = new HashSet<>(user.getFavorites());
        logger.debug("User waiting to be deleted: " + user);
        //logger.debug("Favorite(s) waiting to be deleted" + favoriteDao.getFavoritesByUserId(user));

        userDao.delete(user);
        assertNull(userDao.getUserById(user.getId()));
        assertNotEquals(favorites, favoriteDao.getFavoritesByUserId(user));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = userDao.getByPropertyLike("lastName", "Beeman");
        assertEquals(2, users.size());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = userDao.getByPropertyLike("lastName", "d");
        assertEquals(1, users.size());
    }

    /**
     * Verifies successful insert with favorite
     */
    @Test
    void insertWithFavoriteSuccess() {
        User newUser = new User("Zyn123", "student", "adk3223@madisoncollege.edu", "Mike", "Tomlin", 34596);
        Favorite newFavorite = new Favorite(newUser,"Vitense", 432345445, "9543 Whitney way", 53765, "WI", 20.43, 3.21);

        newUser.addFavorite(newFavorite);

        int id = userDao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = userDao.getUserById(id);
        assertEquals("Zyn123", insertedUser.getUserName());
        assertEquals(1, insertedUser.getFavorites().size());
    }
}