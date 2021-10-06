package persistence;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Uses Hibernate to get a user by their id
     * @param id int
     * @return user object
     */
    public User getUserById(int id) {
        //Create Connection
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        //Close session
        session.close();
        return user;
    }


    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public int insert(User user) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a user
     * @param user User to be deleted
     */
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    /**
     * get all users from the 'user' table in the LocalLinks database
     * @return List of Users objects
     */
    public List<User> getAllUsers() {

        //Create Connection
       Session session = sessionFactory.openSession();
       //Allows to build a query for an entity
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<User> query = builder.createQuery(User.class);
       //builds FROM clause
       Root<User> root = query.from(User.class);
       //Specify running the query
       List<User> users = session.createQuery(query).getResultList();
       logger.debug("The list of all users" + users);
       //Close session
        session.close();

       return users;
    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<User> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery( User.class );
        Root<User> root = query.from( User.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<User> users = session.createQuery( query ).getResultList();

        session.close();
        return users;
    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<User> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery( User.class );
        Root<User> root = query.from( User.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<User> users = session.createQuery( query ).getResultList();
        session.close();
        return users;
    }

    /**
     * get all users using a firstName as the search term
     * @param firstName String representing first name to search for
     * @return List of User objects with first name of firstName param
     */
    public List<User> getUsersByFirstName(String firstName) {

        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        //builds FROM clause
        Root<User> root = query.from(User.class);
        //By Last Name
        Expression<String> propertyPath = root.get("firstName");
        query.where(builder.like(propertyPath, "%" + firstName + "%"));
        //Specify running the query
        List<User> users = session.createQuery(query).getResultList();
        //Close session
        session.close();

        return users;
    }

    /**
     * get all users using a lastName as the search term
     * @param lastName String representing last name to search for
     * @return List of User objects with last name of lastName param
     */
    public List<User> getUsersByLastName(String lastName) {

        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        //builds FROM clause
        Root<User> root = query.from(User.class);
        //By Last Name
        Expression<String> propertyPath = root.get("lastName");
        query.where(builder.like(propertyPath, "%" + lastName + "%"));
        //Specify running the query
        List<User> users = session.createQuery(query).getResultList();
        //Close session
        session.close();

        return users;
    }

    /**
     * get all users using a username as the search term
     * @param userName String representing username to search for
     * @return List of User objects with username of username param
     */
    public List<User> getUsersByUsername(String userName) {

        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        //builds FROM clause
        Root<User> root = query.from(User.class);
        //By Last Name
        Expression<String> propertyPath = root.get("userName");
        query.where(builder.like(propertyPath, "%" + userName + "%"));
        //Specify running the query
        List<User> users = session.createQuery(query).getResultList();
        //Close session
        session.close();

        return users;
    }



}
