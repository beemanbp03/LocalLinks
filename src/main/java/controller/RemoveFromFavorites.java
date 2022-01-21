package controller;



import entity.Favorite;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.Dao;
import util.PropertiesLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//Annotations making it easier to get to this servlet on a browser
@WebServlet(
        name = "removeFromFavorites",
        urlPatterns = { "/removeFromFavorites" }
)

/**
 * This class removes favorites from the database
 */
public class RemoveFromFavorites extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                   the HttpServletRequest object
     *@param  res                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        Set<String> paramNameList = new HashSet<>();
        Favorite result = new Favorite();
        Dao dao = new Dao();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        String attribute = req.getParameter("result");
        logger.info("RESULT: " + result);
        List<Favorite> favorites = dao.getFavoritesByUserId(user);
        for (Favorite favorite : favorites) {
            if (favorite.getPlace_id().equals(attribute)) {
                result.setId(favorite.getId());
                result.setUser(favorite.getUser());
                result.setPlace_id(favorite.getPlace_id());
                result.setLat(favorite.getLat());
                result.setLng(favorite.getLng());
                result.setRating(favorite.getRating());
            }
        }
        logger.info("FAVORITE: " + result);
        dao.deleteFavorite(result);

        String url;
        String[] urlStrings;
        String requestHeader = req.getHeader("Referer");
        logger.info("REQUEST HEADER: " + requestHeader);
        if (requestHeader.contains("golfCourseSearchResults")) {
            urlStrings = requestHeader.split("golfCourseSearchResults");
            url = "/golfCourseSearchResults" + urlStrings[1];
            session.setAttribute("golfCourseSearchURL", url);

            if (session.getAttribute("golfCourseSearchURL") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher((String)session.getAttribute("golfCourseSearchURL"));
                dispatcher.forward(req, res);
            }
            else {
                RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, res);
            }
            for(String item : urlStrings) {
                logger.info("STRING" + item.indexOf(item) + ": " + item);
            }
        } else if (requestHeader.contains("profile")){
            url = "/profile";
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        } else if (requestHeader.contains("removeFromFavorites")) {
            url = "/profile";
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        }

    }

}