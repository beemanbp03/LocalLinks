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

class DaoTest {

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
        assertEquals(1, retrievedUser.getId());
    }


    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "Williams";
        User userToUpdate = userDao.getUserById(1);
        userToUpdate.setLastName(newLastName);
        userDao.saveOrUpdate(userToUpdate);
        User retrievedUser = userDao.getUserById(1);

        logger.info("\n\nRetrievedUser: " + retrievedUser + "\nUserToUpdate: " + userToUpdate);
        assertEquals(userToUpdate, retrievedUser);
    }


    /**
     * Verifies successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("Packers2021", "Lombardi", "kuhn@madisoncollege.edu", "John", "Kuhn", 53932);

        //find the id of the newly created User object from above.  insert(User user) returns an int representing the
        //id of the newly made table row in the database's user table
        int id = userDao.insert(newUser);

        //Make sure the id of the new User is NOT NULL
        assertNotEquals(0, id);

        //Retrieve the newly created User by its id
        User insertedUser = userDao.getUserById(id);

        logger.info("\n\nRetrievedUser: " + insertedUser + "\nUserToUpdate: " + newUser);
        assertEquals(newUser, insertedUser);
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
        //User the method to test "getByPropertyLike(propertyPath, value)"
        List<User> retrievedUsers = userDao.getByPropertyEqual("lastName", "Beeman");

        //Make expected Users and add them to the expectedUsers ArrayList
        List<User> expectedUsers = new ArrayList<User>();
        User expectedUser1 = new User("beemanbp03", "student", "bpbeeman@madisoncollege.edu", "Boulder", "Beeman", 53704);
        User expectedUser2 = new User("dexter04", "student", "dexter@madisoncollege.edu", "Dexter", "Beeman", 53805);
        expectedUser1.setId(1);
        expectedUser2.setId(2);
        expectedUsers.add(expectedUser1);
        expectedUsers.add(expectedUser2);

        //Output both Lists that are about to be compared
        logger.info("\n\nExpected: " + expectedUsers + "\nActual: " + retrievedUsers);

        //Loop through both arrayLists for expectedUsers and retrievedUsers and assert they are equal
        for (int i=0; i < retrievedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), retrievedUsers.get(i));
        }

    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> retrievedUsers = userDao.getByPropertyLike("lastName", "o");
        //Make expected Users and add them to the expectedUsers ArrayList
        List<User> expectedUsers = new ArrayList<User>();
        User expectedUser1 = new User("JohnDoe117", "student", "johndoe@madisoncollege.edu", "John", "Doe", 53929);
        expectedUser1.setId(3);
        expectedUsers.add(expectedUser1);

        //Output both Lists that are about to be compared
        logger.info("\n\nExpected: " + expectedUsers + "\nActual: " + retrievedUsers);

        //Loop through both arrayLists for expectedUsers and retrievedUsers and assert they are equal
        for (int i=0; i < retrievedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i), retrievedUsers.get(i));
        }
    }

    /**
     * Verifies successful insert with favorite
     * !!!!!! This test is probably going to be pointless, because in order for this scenario to exist, a new user has
     * !!!!!! to be able to add favorites while they are creating their account. Not saying I won't explore this as a
     * !!!!!! possible functionality, but it might be time-consuming and a nightmare to implement. therefore, I am not
     * !!!!!! taking the time to refactor the code. COPY/PASTE the @Test above for blueprint on code refactor
     */
    @Test
    void insertWithFavoriteSuccess() {
        User newUser = new User("Zyn123", "student", "adk3223@madisoncollege.edu", "Mike", "Tomlin", 34596);
        Favorite newFavorite = new Favorite("Vitense", 432345445, "9543 Whitney way", 53765, "WI", 20.43, 3.21, newUser);

        newUser.addFavorite(newFavorite);

        int id = userDao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = userDao.getUserById(id);
        assertEquals("Zyn123", insertedUser.getUserName());
        assertEquals(1, insertedUser.getFavorites().size());
    }
}