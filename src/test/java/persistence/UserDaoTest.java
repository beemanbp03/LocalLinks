package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;
    @BeforeEach
    void setUp() {
        dao = new UserDao();
    }

    /**
     * Verifies gets all users successfully
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(1, users.size());
    }

    /**
     * Verifies gets user by last name successfully
     */
    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = dao.getUsersByLastName("Beeman");
        assertEquals(1, users.size());
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
        User retrievedUser = dao.getUserById(2);
        assertEquals(newLastName, retrievedUser.getLastName());
    }

    /**
     * Verifies successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("dexter115", "DexterIsCool", "dexter@madisoncollege.edu", "Dexter", "Beeman", 53704);
        int id = dao.insert(newUser);
        assertNotEquals(0, id);
        User insertedUser = dao.getUserById(id);
        assertEquals("Dexter", insertedUser.getFirstName());
    }

    /**
     * Verifies Successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getUserById(2));
        assertNull(dao.getUserById(2));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "Curry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getUserID());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "c");
        assertEquals(3, users.size());
    }
}