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
        name = "addToFavorites",
        urlPatterns = { "/addToFavorites" }
)

/**
 * This class adds favorites to the database
 */
public class AddToFavorites extends HttpServlet implements PropertiesLoader {

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
        List<Favorite> attrList = new ArrayList<Favorite>();
        Favorite result = new Favorite();
        Dao dao = new Dao();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        Enumeration<String> requestParameters = (Enumeration<String>)req.getParameterNames();

        while (requestParameters.hasMoreElements()) {
            paramNameList.add(requestParameters.nextElement());
        }

        for (String item : paramNameList) {
            logger.info("Item: " + item);
            String attribute = req.getParameter(item);
            result = formatApiResultFromString(attribute, user);
            dao.insertFavorite(result);
        }

        req.setAttribute("result", result);


        String[] urlStrings;
        String requestHeader = req.getHeader("Referer");
        if (requestHeader.contains("golfCourseSearchResults")) {
            urlStrings = requestHeader.split("golfCourseSearchResults");
            for(String item : urlStrings) {
                logger.info("STRING" + item.indexOf(item) + ": " + item);
            }
        } else {
            String tempUrlStrings = (String)session.getAttribute("golfCourseSearchURL");
            urlStrings = tempUrlStrings.split("/golfCourseSearchResults");
            for(String item : urlStrings) {
                logger.info("STRING" + item.indexOf(item) + ": " + item);
            }
        }

        String url = "/golfCourseSearchResults" + urlStrings[1];
        session.setAttribute("golfCourseSearchURL", url);

        if (session.getAttribute("golfCourseSearchURL") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher((String)session.getAttribute("golfCourseSearchURL"));
            dispatcher.forward(req, res);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        }


    }

    public Favorite formatApiResultFromString(String item, User user) {
        Favorite result = new Favorite();
        String[] stringOfItems = item.split("\u0020");

            result.setUser(user);
            result.setPlace_id(stringOfItems[0].trim());
            result.setLat(stringOfItems[1].trim());
            result.setLng(stringOfItems[2].trim());
            result.setRating(Double.valueOf(stringOfItems[3].trim()));

            logger.info("String: " + result);

        return result;
    }
}