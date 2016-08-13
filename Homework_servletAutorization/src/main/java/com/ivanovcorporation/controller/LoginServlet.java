package com.ivanovcorporation.controller;

import com.ivanovcorporation.domain.Customer;
import com.ivanovcorporation.domain.User;
import com.ivanovcorporation.service.CustomerService;
import com.ivanovcorporation.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

public class LoginServlet extends HttpServlet {

    Date date = new Date(1L);
    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {

        if (userService.getByLogin("super") == null) {

            CustomerService customerService = new CustomerService();

            Customer customer = new Customer(
                    "super",
                    "super",
                    date.valueOf("1900-01-01"),
                    "super",
                    "super",
                    "super",
                    "super"
            );
            Long idOfInsertedCustomer = customerService.save(customer);
            userService.save(new User(
                    null,
                    "super",
                    "super",
                    "super",
                    "Super",
                    idOfInsertedCustomer
            ));
        }
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.write(

                " <html> " +
                        " <head> " +
                        " <meta charset=\"utf-8\"> " +
                        " <title>welcome</title> " +
                        " <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\"> " +
                        " <script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js\"></script>" +
                        " <script src=\"/javascript/script.js\"></script> " +
                        " </head> " +
                        " <body> " +
                        " <header> " +
                        "<p> </p>" +
                        " </header> " +
                        " <div class=\"mainContentBackground\"> " +
                        " <p class=\"fontBigCapitals\"> Welcome to the bank!</p> " +

                        "<form action='/' method='post' id=\"Login\">" +
                        "<p> <input type='text' name='login' placeholder='Login'/></p> " +
                        "<p> <input type='password' name='password' placeholder='Password'/></p> " +
                        "<button type='submit'>Login</button> " +
                        "<button type='button' onClick='location.href=\"/Users\"' style=\"position:relative;left:-15px\">Registration</button> " +
                        "</form>" +
                        "<p> </p>" +

                        " </div> " +
                        " </body> " +
                        " </html> "
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user;

        if (login.equals("super") && password.equals("super")) {

            user = userService.getByLogin("super");

        } else {

            user = userService.getByLogin(login);
        }

        if (user != null
                && (user.getPassword().equals(DigestUtils.md5Hex(password).toString().substring(0, 20)) ||
                user.getPassword().equals(password))) {

            HttpSession session = req.getSession(true);

            session.setAttribute("login", new String(user.getLogin()));
            session.setAttribute("role", new String(user.getRole()));
            session.setAttribute("customerID", new Long(user.getCustomerid()));

            resp.sendRedirect("/Index");

        } else {

            resp.sendRedirect("/Users/nouser");
        }
    }
}
