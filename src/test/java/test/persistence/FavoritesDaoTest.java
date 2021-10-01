package test.persistence;

import entity.Favorite;
import entity.User;
import persistence.FavoriteDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.UserDao;
import test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritesDaoTest {

    FavoriteDao dao;
    @BeforeEach
    void setUp() {
        dao = new FavoriteDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all Favorites successfully
     */
    @Test
    void getAllFavoritesSuccess() {
        List<Favorite> favorites = dao.getAllFavorites();
        assertEquals(1, favorites.size());
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

    @Test
    void saveOrUpdateSuccess() {
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
    void insertSuccess() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(1);
        Favorite newFavorite = new Favorite(user, "Top Golf", 608233445, "2342 Lindale ave", 53872, "WI", 3.40, 4.57);
        int id = dao.insert(newFavorite);
        assertNotEquals(0, id);
        Favorite insertedFavorite = dao.getFavoriteById(id);
        assertEquals("Top Golf", insertedFavorite.getName());
        assertEquals("Boulder", insertedFavorite.getUser().getFirstName());
    }

    /**
     * Verifies Successful delete of Favorite
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getFavoriteById(1));
        assertNull(dao.getFavoriteById(1));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Favorite> Favorites = dao.getByPropertyLike("name", "Top Golf");
        assertEquals(1, Favorites.size());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Favorite> Favorites = dao.getByPropertyLike("name", "t");
        assertEquals(1, Favorites.size());
    }

}
