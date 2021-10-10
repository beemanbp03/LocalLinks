package test.persistence;

import entity.Favorite;
import entity.User;
import org.hibernate.SessionFactory;
import persistence.FavoriteDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.SessionFactoryProvider;
import persistence.UserDao;
import test.util.Database;
import persistence.UserDao;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritesDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    
    UserDao userDao;
    FavoriteDao favoriteDao;

    @BeforeEach
    void setUp() {
        favoriteDao = new FavoriteDao();
        userDao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all Favorites successfully
     */
    @Test
    void getAllFavoritesSuccess() {
        List<Favorite> favorites = favoriteDao.getAllFavorites();
        assertEquals(4, favorites.size());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
     */
    @Test
    public void getFavoritesByNameSuccess() {
        List<Favorite> retrievedFavorite = favoriteDao.getFavoritesByName("Top Golf");
        assertEquals(1, retrievedFavorite.size());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
     */
    @Test
    public void getFavoritesByIdSuccess() {
        Favorite retrievedFavorite = favoriteDao.getFavoriteById(1);
        assertEquals("Top Golf", retrievedFavorite.getName());
    }

    /**
     * Verifies a Favorite is returned correctly based on ID search
    */
    @Test
    public void getFavoritesByUserIdSuccess() {
        User user = favoriteDao.getFavoriteById(1).getUser();
        Set<Favorite> favorites = new HashSet<>();
        favorites = favoriteDao.getFavoritesByUserId(user);
        assertNotNull(favorites);
    }


    @Test
    void saveOrUpdateSuccess() {
        String newName = "Pine Valley Golf Course";
        Favorite favoriteToUpdate = favoriteDao.getFavoriteById(1);
        favoriteToUpdate.setName("Pine Valley Golf Course");
        favoriteDao.saveOrUpdate(favoriteToUpdate);
        Favorite retrievedFavorite = favoriteDao.getFavoriteById(1);
        assertEquals(newName, retrievedFavorite.getName());
    }

    /**
     * Verifies successful insert of a Favorite
     */
    @Test
    void insertSuccess() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(1);
        Favorite newFavorite = new Favorite("Pairie Hills Golf Course", 608233445, "2342 Lindale ave", 53872, "WI", 3.40, 4.57, user);
        int id = favoriteDao.insert(newFavorite);
        assertNotEquals(0, id);
        Favorite insertedFavorite = favoriteDao.getFavoriteById(id);
        assertEquals("Pairie Hills Golf Course", insertedFavorite.getName());
        assertEquals("Boulder", insertedFavorite.getUser().getFirstName());
    }

    /**
     * Verifies Successful delete of Favorite
     */
    @Test
    void deleteSuccess() {
        Favorite favoriteToDelete = favoriteDao.getFavoriteById(1);
        User userFromDeletedFavorite = favoriteToDelete.getUser();

        logger.info("FAVORITE TO DELETE: favorite ID of " + favoriteToDelete.getId());
        favoriteDao.delete(favoriteToDelete);
        //Make sure the user that corresponded to the deleted favorite still exists
        assertNotNull(userDao.getUserById(1));
        //Make sure the favorite to delete was actually deleted
        assertNull(favoriteDao.getFavoriteById(1));
        // Make sure that not all favorites got deleted (there are 2 Favorite objects for the user at the start of this
        // method, so now there should be only one, which means the List<Favorite> still has one object left, making it
        // not null
        assertNotNull(userFromDeletedFavorite.getFavorites());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        //The method to test "getByPropertyEqual()"
        List<Favorite> retrievedFavorites = favoriteDao.getByPropertyEqual("name", "Top Golf");

        //Make expected Favorites and add them to the expectedFavorites ArrayList
        List<Favorite> expectedFavorites = new ArrayList<Favorite>();
        User expectedUser = userDao.getUserById(1);
        Favorite expectedFavorite1 = favoriteDao.getFavoriteById(1);
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
    void getByPropertyLikeSuccess() {
        List<Favorite> retrievedFavorites = favoriteDao.getByPropertyLike("name", "g");

        //Make expected Favorites and add them to the expectedFavorites ArrayList
        List<Favorite> expectedFavorites = new ArrayList<Favorite>();
        Favorite expectedFavorite1 = favoriteDao.getFavoriteById(1);
        Favorite expectedFavorite2 = favoriteDao.getFavoriteById(2);
        Favorite expectedFavorite3 = favoriteDao.getFavoriteById(3);
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
