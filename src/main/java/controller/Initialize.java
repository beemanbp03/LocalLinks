package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PropertiesLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;


@WebServlet(
        name = "initialize",
        urlPatterns = {"/initialize"},
        loadOnStartup = 1
)

/**
 * A simple servlet to welcome the user.
 * @author bpbeeman
 */
public class Initialize extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    Properties properties;
    String test = "!!!!THIS IS A TEST!!!!!";


    public void init() {
        try {
            properties = loadProperties("/cognito.properties");
            ServletContext context = getServletContext();
            context.setAttribute("test", "This is a test");
            context.setAttribute("cognitoProperties", properties);
        } catch (Exception e) {
            logger.error("Error", e);
        }

    }


    /**
     *  Handles HTTP GET requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        //Create int to hold session counts
        String userName = null;
        Integer sessionCounter;

        //create a session variable
        HttpSession session = request.getSession();

        //GET the sessionCounter attribute from the session and assign it to sessionCounter instance variable
        sessionCounter = (Integer) session.getAttribute("sessionCounter");

        //If null, set sessionCounter and set attribute, else increment\
        //sessionCounter and set the attribute
        if (sessionCounter == null) {
            sessionCounter = 1;
            session.setAttribute("sessionCounter", sessionCounter);
        } else {
            sessionCounter = sessionCounter + 1;
            session.setAttribute("sessionCounter", sessionCounter);
        }

        String url = "/home";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
