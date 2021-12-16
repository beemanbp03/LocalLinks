package controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.Dao;
import persistence.SessionFactoryProvider;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@WebServlet(
        urlPatterns = {"/searchUser"}
)

/**
 * This servlet allows us to search for a Users and Favorites
 */

public class UserSearch extends HttpServlet {


    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Dao dao = new Dao();

        //HTTP form info || set attributes
        String searchTermUser = req.getParameter("searchTermUser");
        String searchTypeUser = req.getParameter("searchTypeUser");

        String searchTermFavorite = req.getParameter("searchTermFavorite");
        String searchTypeFavorite = req.getParameter("searchTypeFavorite");

        try {
            /**
             * Section that supplies the userResults.jsp with User data
             */
            if ( req.getParameter("submit").equals("searchUser")) {

                if (searchTypeUser.equals("id")) {
                    try {
                        int id = Integer.parseInt(searchTermUser);
                        req.setAttribute("user", dao.getUserById(id));
                    } catch(Exception e) {
                        logger.debug("SOME SORT OF ERROR HAPPENED", e);
                    }

                } else if (searchTypeUser.equals("user_name")) {

                    req.setAttribute("user", dao.getUserByUsername(searchTermUser));

                } else if (searchTypeUser.equals("first_name")) {

                    req.setAttribute("user", dao.getUserByFirstName(searchTermUser));

                } else if (searchTypeUser.equals("last_name")) {

                    req.setAttribute("user", dao.getUserByLastName(searchTermUser));

                }

            }
            /**
             * Section that Supplies the userResults.jsp with ALL USERS
             */
            else if (req.getParameter("submit").equals("searchUserAll")) {
                req.setAttribute("users", dao.getAllUsers());
            }
            /**
             * Section that supplies the userResults.jsp with Favorites data
             */
            else if (req.getParameter("submit").equals("searchFavorite")){
                if (searchTypeFavorite != null) {
                    if (searchTypeFavorite.equals("id")) {

                        req.setAttribute("favorites", dao.getFavoriteById(Integer.parseInt(searchTermFavorite)));

                    } else if (searchTypeFavorite.equals("name")) {

                        req.setAttribute("favorites", dao.getFavoritesByPlaceId(searchTermUser));

                    }
                }
            }
            /**
             * Section that supplies the userResults.jsp with ALL rows of Favorites in the database
             */
            else if (req.getParameter("submit").equals("searchFavoriteAll")) {
                req.setAttribute("favorites", dao.getAllFavorites());
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/userResults.jsp");
        dispatcher.forward(req, resp);
    }
}