package persistence;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

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
       //Close session
        session.close();

       return users;
    }
}
