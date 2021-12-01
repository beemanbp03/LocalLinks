package test.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.SessionFactoryProvider;
import persistence.Dao;

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

    //Initialize Dao class for this DaoTest class so that we can later instantiate it and gain access to its methods
    Dao dao;

    /**
     * Before each test is run, instantiate a new Dao class object, create an instance of the Database class, and
     * wipe it clean before it is used.
     */
    @BeforeEach
    void setUp() {
        dao = new Dao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all Users successfully
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * Verifies gets User by last name successfully
     */
    @Test
    void getUserByLastNameSuccess() {
        List<User> users = dao.getUsersByLastName("Beeman");
        assertEquals(2, users.size());
    }

    /**
     * Verifies a User is returned correctly based on ID search
     */
    @Test
    public void getUserByIdSuccess() {
        User retrievedUser = dao.getUserById(1);
        assertEquals(1, retrievedUser.getId());
    }

    /**
     * Verifies a User row was updated successfully
     */
    @Test
    void saveOrUpdateUserSuccess() {
        String newLastName = "Williams";
        User userToUpdate = dao.getUserById(1);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getUserById(1);

        logger.info("\n\nRetrievedUser: " + retrievedUser + "\nUserToUpdate: " + userToUpdate);
        assertEquals(userToUpdate, retrievedUser);
    }


    /**
     * Verifies successful insert of a User
     */
    @Test
    void insertUserSuccess() {
        User newUser = new User("Packers2021", "Lombardi", "kuhn@madisoncollege.edu", "John", "Kuhn", 53932);

        //find the id of the newly created User object from above.  insert(User user) returns an int representing the
        //id of the newly made table row in the database's user table
        int id = dao.insert(newUser);

        //Make sure the id of the new User is NOT NULL
        assertNotEquals(0, id);

        //Retrieve the newly created User by its id
        User insertedUser = dao.getUserById(id);

        logger.info("\n\nRetrievedUser: " + insertedUser + "\nUserToUpdate: " + newUser);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verifies Successful delete of User
     */
    @Test
    void deleteUserSuccess() {
        User user = dao.getUserById(1);
        Set<Favorite> favorites = new HashSet<>(user.getFavorites());
        logger.debug("User waiting to be deleted: " + user);
        //logger.debug("Favorite(s) waiting to be deleted" + favoriteDao.getFavoritesByUserId(user));

        dao.delete(user);
        assertNull(dao.getUserById(user.getId()));
        assertNotEquals(favorites, dao.getFavoritesByUserId(user));
    }

    /**
     * Verify successful get User by property (equal match)
     */
    @Test
    void getUserByPropertyEqualSuccess() {
        //User the method to test "getByPropertyLike(propertyPath, value)"
        List<User> retrievedUsers = dao.getUserByPropertyEqual("lastName", "Beeman");

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
     * Verify successful get User by property (like match)
     */
    @Test
    void getUserByPropertyLikeSuccess() {
        List<User> retrievedUsers = dao.getUserByPropertyLike("lastName", "o");
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

        int id = dao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = dao.getUserById(id);
        assertEquals("Zyn123", insertedUser.getUserName());
        assertEquals(1, insertedUser.getFavorites().size());
    }





    /**                                        FAVORITES DAO TESTING                                                 **/


    /**
     * Verifies gets all Favorites successfully
     */
    @Test
    void getAllFavoritesSuccess() {
        List<Favorite> favorites = dao.getAllFavorites();
        assertEquals(4, favorites.size());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
     */
    @Test
    public void getFavoritesByNameSuccess() {
        List<Favorite> retrievedFavorite = dao.getFavoritesByName("Top Golf");
        assertEquals(1, retrievedFavorite.size());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
     */
    @Test
    public void getFavoritesByIdSuccess() {
        Favorite retrievedFavorite = dao.getFavoriteById(1);
        assertEquals("Top Golf", retrievedFavorite.getName());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
     */
    @Test
    public void getFavoritesByUserIdSuccess() {
        User user = dao.getFavoriteById(1).getUser();
        Set<Favorite> favorites = new HashSet<>();
        favorites = dao.getFavoritesByUserId(user);
        assertNotNull(favorites);
    }

    /**
     * Verifies a Favorite can be updated successfully
     */
    @Test
    void saveOrUpdateFavoriteSuccess() {
        String newName = "Pine Valley Golf Course";
        Favorite favoriteToUpdate = dao.getFavoriteById(1);
        favoriteToUpdate.setName("Pine Valley Golf Course");
        dao.saveOrUpdate(favoriteToUpdate);
        Favorite retrievedFavorite = dao.getFavoriteById(1);
        assertEquals(newName, retrievedFavorite.getName());
    }

    /**
     * Verifies successful insert of a Favorite
     */
    @Test
    void insertFavoriteSuccess() {
        User user = dao.getUserById(1);
        Favorite newFavorite = new Favorite("Pairie Hills Golf Course", 608233445, "2342 Lindale ave", 53872, "WI", 3.40, 4.57, user);
        int id = dao.insert(newFavorite);
        assertNotEquals(0, id);
        Favorite insertedFavorite = dao.getFavoriteById(id);
        assertEquals("Pairie Hills Golf Course", insertedFavorite.getName());
        assertEquals("Boulder", insertedFavorite.getUser().getFirstName());
    }

    /**
     * Verifies Successful delete of Favorite
     */
    @Test
    void deleteFavoriteSuccess() {
        Favorite favoriteToDelete = dao.getFavoriteById(1);
        User userFromDeletedFavorite = favoriteToDelete.getUser();

        logger.info("FAVORITE TO DELETE: favorite ID of " + favoriteToDelete.getId());
        dao.delete(favoriteToDelete);
        //Make sure the user that corresponded to the deleted favorite still exists
        assertNotNull(dao.getUserById(1));
        //Make sure the favorite to delete was actually deleted
        assertNull(dao.getFavoriteById(1));
        // Make sure that not all favorites got deleted (there are 2 Favorite objects for the user at the start of this
        // method, so now there should be only one, which means the List<Favorite> still has one object left, making it
        // not null
        assertNotNull(userFromDeletedFavorite.getFavorites());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getFavoriteByPropertyEqualSuccess() {
        //The method to test "getByPropertyEqual()"
        List<Favorite> retrievedFavorites = dao.getFavoritesByPropertyEqual("name", "Top Golf");

        //Make expected Favorites and add them to the expectedFavorites ArrayList
        List<Favorite> expectedFavorites = new ArrayList<Favorite>();
        User expectedUser = dao.getUserById(1);
        Favorite expectedFavorite1 = dao.getFavoriteById(1);
        expectedFavorite1.setId(1);
        expectedFavorites.add(expectedFavorite1);

        //Output both Lists that are about to be compared
        logger.info("\n\nExpected: " + expectedFavorites + "\nActual: " + retrievedFavorites);

        //Loop through both arrayLists for expectedFavorites and retrievedFavorites and assert they are equal
        for (int i=0; i < retrievedFavorites.size(); i++) {
            assertEquals(expectedFavorites.get(i), retrievedFavorites.get(i));
        }
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getFavoriteByPropertyLikeSuccess() {
        List<Favorite> retrievedFavorites = dao.getFavoritesByPropertyLike("name", "g");

        //Make expected Favorites and add them to the expectedFavorites ArrayList
        List<Favorite> expectedFavorites = new ArrayList<Favorite>();
        Favorite expectedFavorite1 = dao.getFavoriteById(1);
        Favorite expectedFavorite2 = dao.getFavoriteById(2);
        Favorite expectedFavorite3 = dao.getFavoriteById(3);
        expectedFavorite1.setId(1);
        expectedFavorite2.setId(2);
        expectedFavorite3.setId(3);
        expectedFavorites.add(expectedFavorite1);
        expectedFavorites.add(expectedFavorite2);
        expectedFavorites.add(expectedFavorite3);

        assertEquals(3, retrievedFavorites.size());

        //Output both Lists that are about to be compared
        logger.info("\nExpected: " + expectedFavorites.get(1) + "\nActual: " + retrievedFavorites.get(1) + "\n");

        for (int i=0; i < retrievedFavorites.size(); i++) {
            logger.info("\nRetrievedFavorite: " + retrievedFavorites.get(i) + "\n");
        }

        assertNotNull(retrievedFavorites);
        //Loop through both arrayLists for expectedFavorites and retrievedFavorites and assert they are equal
        for (int i=0; i < retrievedFavorites.size(); i++) {
            assertEquals(expectedFavorites.get(i), retrievedFavorites.get(i));
        }
    }

}