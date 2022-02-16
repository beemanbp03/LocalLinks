package controller;



import entity.ApiResult;
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
        List<Favorite> favorites = dao.getFavoritesByUserId(user);

        String attribute = req.getParameter("result");
        logger.info("RESULT: " + result);

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
        favorites = dao.getFavoritesByUserId(user);

        String url;
        String requestHeader = req.getHeader("Referer");
        req.setAttribute("results", session.getAttribute("results"));
        req.setAttribute("favorites", favorites);

        logger.info("REQUEST HEADER: " + requestHeader);
        if (requestHeader.contains("golfCourseSearchResults")) {
            url = "golfCourseResults.jsp";

            String urlStrings = "searchResults";
            session.setAttribute("returnFromRemove", urlStrings);

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        } else if (requestHeader.contains("profile")){
            url = "/profile";

            String urlStrings = "profile";
            session.setAttribute("returnFromRemove", urlStrings);

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        } else if (requestHeader.contains("removeFromFavorites")) {

            String returnFromRemove = (String)session.getAttribute("returnFromRemove");

            if (returnFromRemove.equals("profile")){
                url = "/profile";
            } else if (returnFromRemove.equals("searchResults")){
                url = "golfCourseResults.jsp";
            } else {
                url = "/home";
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        } else if (requestHeader.contains("addToFavorites")) {

            url = "golfCourseResults.jsp";

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        }

    }

}