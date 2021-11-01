package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.SessionFactoryProvider;
import persistence.WebServiceDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * This servlet searches for golf courses and their weather based on a ZIP code entered by the user
 */

@WebServlet(
        urlPatterns = {"/golfCourseSearchResults"}
)

public class GolfCourseSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebServiceDao webServiceDao = new WebServiceDao();
        String zipCodeParam = req.getParameter("zipCodeSearch");

        req.setAttribute("places", webServiceDao.getPlaces());
        req.setAttribute("weather", webServiceDao.getWeather());

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }

}
