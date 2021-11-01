package controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.FavoriteDao;
import persistence.SessionFactoryProvider;
import persistence.UserDao;
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

public class UserSearch extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        FavoriteDao favoriteDao = new FavoriteDao();

        //HTTP form info || set attributes
        String searchTermUser = req.getParameter("searchTermUser");
        String searchTypeUser = req.getParameter("searchTypeUser");

        String searchTermFavorite = req.getParameter("searchTermFavorite");
        String searchTypeFavorite = req.getParameter("searchTypeFavorite");



        /**
         * Section that supplies the userResults.jsp with User data
         */
        if ( req.getParameter("submit").equals("searchUser")) {

            if (searchTypeUser.equals("id")) {
                try {
                    int id = Integer.parseInt(searchTermUser);
                    req.setAttribute("user", userDao.getUserById(id));
                } catch(Exception e) {
                    logger.debug("SOME SORT OF ERROR HAPPENED", e);
                }

            } else if (searchTypeUser.equals("user_name")) {

                req.setAttribute("users", userDao.getUsersByUsername(searchTermUser));

            } else if (searchTypeUser.equals("first_name")) {

                req.setAttribute("users", userDao.getUsersByFirstName(searchTermUser));

            } else if (searchTypeUser.equals("last_name")) {

                req.setAttribute("users", userDao.getUsersByLastName(searchTermUser));

            } else if (searchTypeUser == null){
                req.setAttribute("users", userDao.getByPropertyLike(searchTypeUser, searchTermUser));
            }

        }
        /**
         * Section that Supplies the userResults.jsp with ALL USERS
         */
        else if (req.getParameter("submit").equals("searchUserAll")) {
            req.setAttribute("users", userDao.getAllUsers());
        }
        /**
         * Section that supplies the userResults.jsp with Favorites data
         */
        else if (req.getParameter("submit").equals("searchFavorite")){
            if (searchTypeFavorite != null) {
                if (searchTypeFavorite.equals("id")) {

                    req.setAttribute("favorite", favoriteDao.getFavoriteById(Integer.parseInt(searchTermUser)));

                } else if (searchTypeFavorite.equals("name")) {

                    req.setAttribute("users", favoriteDao.getFavoritesByName(searchTermUser));

                }
            } else {
                req.setAttribute("users", favoriteDao.getByPropertyLike("name", searchTermUser));
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userResults.jsp");
        dispatcher.forward(req, resp);
    }
}
