package persistence;

import entity.Favorite;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.*;

public class FavoriteDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

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


    /**
     * update Favorite
     * @param Favorite  Favorite to be inserted or updated
     */
    public int insert(Favorite Favorite) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(Favorite);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a Favorite
     * @param  Favorite table row to be deleted
     */
    public void delete(Favorite Favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(Favorite);
        transaction.commit();
        session.close();
    }

    /**
     * update Favorite
     * @param Favorite  Favorite to be inserted or updated
     */
    public void saveOrUpdate(Favorite Favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(Favorite);
        transaction.commit();
        session.close();
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
       //builds FROM clause
       Root<Favorite> root = query.from(Favorite.class);
       //Specify running the query
       List<Favorite> Favorites = session.createQuery(query).getResultList();
       logger.debug("The list of all Favorites" + Favorites);
       //Close session
        session.close();

       return Favorites;
    }

    /**
     * Get Favorite by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Favorite> getByPropertyEqual(String propertyName, String value) {
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

    /**
     * Get Favorite by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Favorite> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Favorite with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Favorite> query = builder.createQuery( Favorite.class );
        Root<Favorite> root = query.from( Favorite.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Favorite> Favorites = session.createQuery( query ).getResultList();
        session.close();
        return Favorites;
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
