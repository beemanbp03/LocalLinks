package controller;


import entity.ApiResult;
import entity.Favorite;
import entity.User;
import entity.details.Details;
import entity.geo.ResultsItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.ApiDao;
import persistence.Dao;
import persistence.SessionFactoryProvider;
import util.PropertiesLoader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Annotations making it easier to get to this servlet on a browser
@WebServlet(
        name = "profile",
        urlPatterns = { "/profile" }
)

/**
 * This class simply forwards to the home page after the application startup
 */
public class Profile extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

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

        //Load favorites from database and put them in request
        Dao dao = new Dao();
        ApiDao apiDao = new ApiDao();
        HttpSession session = req.getSession();

        List<Favorite> userFavorites = dao.getFavoritesByUserId((User) session.getAttribute("user"));
        logger.info("allFavorites: " + userFavorites);

        List<ApiResult> resultList = new ArrayList<>();

        //Loop through Favorites array, call the APIs using their methods, then send results array to request
        for (Favorite item : userFavorites) {
            try {
                ApiResult result = new ApiResult();
                Details details = apiDao.getDetails(item.getPlace_id());
                logger.info("Details Object: " + details);

                //set all you can on ApiResult
                double lat = details.getResult().getGeometry().getLocation().getLat();
                double lng = details.getResult().getGeometry().getLocation().getLng();
                result.setName(details.getResult().getName());
                result.setPlace_id(details.getResult().getPlaceId());
                result.setLat(String.valueOf(lat));
                result.setLng(String.valueOf(lng));
                result.setVicinity("BLAH");
                result.setCall(details.getResult().getFormattedPhoneNumber());
                result.setUrl(details.getResult().getUrl());
                result.setRating(String.valueOf(item.getRating()));
                result.setHourlyWeather(apiDao.fetchWeather(lat, lng).getHourlyWeather());

                //Add the result to the result array that will be passed to the web page
                logger.info("Result: " + result);
                resultList.add(result);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        logger.info("resultList: " + resultList);
        req.setAttribute("favorites", resultList);


        String url = "/profile.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }
}