package persistence;


import entity.User;
import entity.Favorite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This DAO handles CRUD for both User and Favorite tables in the LocalLinks database using hibernate
 */

public class Dao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**                                            Get By id                                                         **/

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
     * Uses Hibernate to get a Favorite by their id
     * @param id int
     * @return Favorite object
     */
    public Favorite getFavoriteById(int id) {
        //Create Connection
        Session session = sessionFactory.openSession();
        Favorite Favorite = session.get(Favorite.class, id);
        //Close session
        session.close();
        return Favorite;
    }


    /**                                                Insert                                                        **/

    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public int insertUser(User user) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * update Favorite
     * @param Favorite  Favorite to be inserted or updated
     */
    public int insertFavorite(Favorite Favorite) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(Favorite);
        transaction.commit();
        session.close();
        return id;
    }


    /**                                                Delete                                                        **/

    /**
     * Delete a user
     * @param user User to be deleted
     */
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    /**
     * Delete a Favorite
     * @param  Favorite table row to be deleted
     */
    public void deleteFavorite(Favorite Favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(Favorite);
        transaction.commit();
        session.close();
    }


    /**                                              Save Or Update                                                  **/

    /**
     * update user
     * @param user  User to be inserted or updated
     */
    public void saveOrUpdateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    /**
     * update Favorite
     * @param Favorite  Favorite to be inserted or updated
     */
    public void saveOrUpdateFavorite(Favorite Favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(Favorite);
        transaction.commit();
        session.close();
    }


    /**                                                Get All                                                       **/

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
        query.select(root);
        //Specify running the query
        List<User> users = session.createQuery(query).getResultList();
        logger.debug("The list of all users" + users);
        //Close session
        session.close();

        return users;
    }

    /**
     * get all Favorites from the 'Favorite' table in the LocalLinks database
     * @return List of Favorites objects
     */
    public List<Favorite> getAllFavorites() {

        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery(Favorite.class);
        //Builds FROM clause
        Root<Favorite> root = query.from(Favorite.class);
        //Specify running the query
        List<Favorite> Favorites = session.createQuery(query).getResultList();
        logger.debug("The list of all Favorites" + Favorites);
        //Close session
        session.close();

        return Favorites;
    }


    /**                                          Get By Property Equals                                              **/

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<User> getUserByPropertyEqual(String propertyName, String value) {
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
     * Get Favorite by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Favorite> getFavoritesByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Favorite with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery( Favorite.class );
        Root<Favorite> root = query.from( Favorite.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Favorite> Favorites = session.createQuery( query ).getResultList();

        session.close();
        return Favorites;
    }


    /**                                           Get By Property Like                                               **/
    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<User> getUsersByPropertyLike(String propertyName, String value) {
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
     * Get Favorite by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Favorite> getFavoritesByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery( Favorite.class );
        Root<Favorite> root = query.from( Favorite.class );
        query.select(root).where(builder.like(root.get(propertyName), "%" + value + "%"));

        List<Favorite> favorites = session.createQuery( query ).getResultList();


        //Output each Favorite's foreign key
        for (int i=0; i < favorites.size(); i++) {
            Favorite favorite = favorites.get(i);
            System.out.println("\n\n\n\n" + favorites.get(i));
        }

        session.close();
        return favorites;
    }


    /**                                              Get By Name                                                     **/

    /**
     * get all users using a firstName as the search term
     * @param firstName String representing first name to search for
     * @return List of User objects with first name of firstName param
     */
    public List<User> getUserByFirstName(String firstName) {

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
    public List<User> getUserByLastName(String lastName) {

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
     * get all Favorites using a lastName as the search term
     * @param name String representing name to search for
     * @return List of Favorite objects with name of name param
     */
    public List<Favorite> getFavoritesByName(String name) {

        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery(Favorite.class);
        //builds FROM clause
        Root<Favorite> root = query.from(Favorite.class);
        //By name
        Expression<String> propertyPath = root.get("name");
        query.where(builder.like(propertyPath, "%" + name + "%"));
        //Specify running the query
        List<Favorite> Favorites = session.createQuery(query).getResultList();
        //Close session
        session.close();

        return Favorites;
    }


    /**                                              Get By Username                                                     **/
    /**
     * get all users using a username as the search term
     * @param userName String representing username to search for
     * @return List of User objects with username of username param
     */
    public List<User> getUserByUsername(String userName) {

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


    /**                                           Get By User id (foreign key)                                        */


    public Set<Favorite> getFavoritesByUserId(User user) {
        String userId = String.valueOf(user.getId());
        //Create Connection
        Session session = sessionFactory.openSession();
        //Allows to build a query for an entity
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery(Favorite.class);
        //builds FROM clause
        Root<Favorite> root = query.from(Favorite.class);
        //By user
        Path<Favorite> propertyPath = root.join("user").get("id");
        query.select(propertyPath);
        //Specify running the query
        List<Favorite> favorites = session.createQuery(query).getResultList();
        Set<Favorite> favoritesSet = new HashSet<>(favorites);

        //Close session
        session.close();

        return favoritesSet;
    }


}
