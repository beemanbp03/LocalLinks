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
        assertEquals(2, favorites.size());
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
        Favorite newFavorite = new Favorite(user, "Pairie Hills Golf Course", 608233445, "2342 Lindale ave", 53872, "WI", 3.40, 4.57);
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
        List<Favorite> Favorites = favoriteDao.getByPropertyLike("name", "Top Golf");
        assertEquals(1, Favorites.size());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Favorite> Favorites = favoriteDao.getByPropertyLike("name", "t");
        assertEquals(1, Favorites.size());
    }

}
