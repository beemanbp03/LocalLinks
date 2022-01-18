package controller;


import util.PropertiesLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Annotations making it easier to get to this servlet on a browser
@WebServlet(
        name = "addToFavorites",
        urlPatterns = { "/addToFavorites" }
)

/**
 * This class adds favorites to the database
 */
public class DynamicDaoServlet extends HttpServlet implements PropertiesLoader {

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

        String message = req.getParameter("message");

        req.setAttribute("message", message);

        String url = "/addFavoritesSuccess.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }
}