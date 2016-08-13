package com.ivanovcorporation.controller;

import com.ivanovcorporation.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Сергей on 06.03.2016.
 */
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!req.getRequestURI().equals("/Index")) {

            resp.sendError(404);
            return;
        }

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        String login = (String) session.getAttribute("login");
        UserService userService = new UserService();

        PrintWriter writer = resp.getWriter();

        writer.print(
        " <html> " +
                " <head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>welcome</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\"> " +
                " </head> " +
                " <body> " +
                " <header> " +
                " <ul> " +

                (role.equals("User") ? "" : "<li><a href=\"/Customers\">Customers</a></li>") +

                (role.equals("User") ? "<li><a href=\"/Accounts/User?id="
                        + userService.getByLogin(login).getCustomerid()
                        + "\">Accounts</a></li>"
                        : "<li><a href=\"/Accounts\">Accounts</a></li>") +

                (role.equals("User") ? "<li><a href=\"/Transactions/User?id="
                        + userService.getByLogin(login).getCustomerid()
                        + "\">Transactions</a></li>"
                        : "<li><a href=\"/Transactions\">Transactions</a></li>") +

                (role.equals("Super") ? "<li><a href=\"/Admins"
                        + "\">Promote or disrank a user or admin</a></li>"
                        : "") +

                " </ul> " +
                " </header> " +
                " <div class=\"mainContentBackground\"> " +
                " <p class=\"fontBigCapitals\"> Welcome to the bank!</p> " +

                "       <form action=\"Customers/logout\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"id\" value=\"logout\"/>" +
                "       <button type=\"submit\">Logout</button>" +
                "       </form>" +
                " <p> </p> " +


                " </div> " +
                " </body> " +
                " </html> "
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String uri = req.getRequestURI();
        String command = uri.substring(uri.lastIndexOf("/") + 1);

        switch (command) {

            case "logout":

                HttpSession session = req.getSession();

                session.invalidate();

                break;
        }
    }
}
