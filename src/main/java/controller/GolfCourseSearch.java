package controller;

import entity.geo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.GeoCodeDao;
import persistence.SessionFactoryProvider;
import persistence.GoogleApiDao;
import persistence.WeatherApiDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(
        urlPatterns = {"/golfCourseSearchResults"}
)

/**
 * This servlet searches for golf courses and their weather based on a ZIP code entered by the user
 */
public class GolfCourseSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GoogleApiDao googleServiceDao = new GoogleApiDao();
        WeatherApiDao weatherServiceDao = new WeatherApiDao();
        GeoCodeDao geoCodeDao = new GeoCodeDao();
        String zipCodeParam = req.getParameter("zipCodeSearch");
        int zipCode = Integer.parseInt(zipCodeParam);
        GeoCode geo = null;

        try {
            geo = geoCodeDao.getLatLong(zipCode);
            List<ResultsItem> geoResults = geo.getResults();
            double lat = geoResults.get(0).getGeometry().getLocation().getLat();
            double lng = geoResults.get(0).getGeometry().getLocation().getLng();

            req.setAttribute("places", googleServiceDao.getPlaces(50, lat, lng));
            //What I need to do is get the zip codes of all the google places results, store them in a list,
            //Call the getWeather() method, store those weather results in a hashSet or just an array, then send
            //that array to the JSP in order to match the correct weather with the correct golf course
            req.setAttribute("weather", weatherServiceDao.getWeather());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }

}
