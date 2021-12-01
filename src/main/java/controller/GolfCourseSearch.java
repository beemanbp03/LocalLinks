package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
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
        String zipCodeParam = req.getParameter("zipCodeSearch");

        try {
            req.setAttribute("places", googleServiceDao.getPlaces(50));
            req.setAttribute("weather", weatherServiceDao.getWeather());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }

}
