
package edu.matc.controller;


import edu.matc.entity.Contact;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet
        (urlPatterns = { "/userLogin" } )

public class UserLogin extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<User> genericDao = DaoFactory.createDao(User.class);

        User newUser = new User(req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("user_name"),
                req.getParameter("password"),
                req.getParameter("email"));

        int newId = genericDao.insert(newUser);

        User user = genericDao.getById(newId);
        req.setAttribute("newUser", user);
        logger.debug("User Redirect..." + user);

        RequestDispatcher dispatcher = req.getRequestDispatcher("SubmitLogin");
        dispatcher.forward(req, resp);
    }

}