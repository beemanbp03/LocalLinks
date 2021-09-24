package test.persistence;

import persistence.UserDao;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;
    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all users successfully
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * Verifies gets user by last name successfully
     */
    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = dao.getUsersByLastName("Beeman");
        assertEquals(2, users.size());
    }

    /**
     * Verifies a user is returned correctly based on ID search
     */
    @Test
    public void getUsersByIdSuccess() {
        User retrievedUser = dao.getUserById(1);
        assertEquals("Boulder", retrievedUser.getFirstName());
    }


    @Test
    void saveOrUpdateSuccess() {
        String newLastName = "Williams";
        User userToUpdate = dao.getUserById(2);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getUserById(2);
        assertEquals(newLastName, retrievedUser.getLastName());
    }

    /**
     * Verifies successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("Packers2021", "Lombardi", "kuhn@madisoncollege.edu", "John", "Kuhn", 53932);
        int id = dao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = dao.getUserById(id);
        assertEquals("John", insertedUser.getFirstName());
    }

    /**
     * Verifies Successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getUserById(1));
        assertNull(dao.getUserById(1));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "Beeman");
        assertEquals(2, users.size());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "d");
        assertEquals(1, users.size());
    }
}