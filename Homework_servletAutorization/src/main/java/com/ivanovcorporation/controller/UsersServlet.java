package com.ivanovcorporation.controller;

import com.ivanovcorporation.domain.Customer;
import com.ivanovcorporation.domain.User;
import com.ivanovcorporation.service.CustomerService;
import com.ivanovcorporation.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    Date date = new Date(1L);
    private CustomerService customerService = new CustomerService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        if (req.getRequestURI().equals("/Users")) {

            writer.write(generateUserHtml());
            return;
        }

        if (req.getRequestURI().equals("/Users/nouser")) {

            writer.write(generateUserDoesntExistHtml());

            return;
        }

        if (req.getRequestURI().equals("/Users/reg")) {


            writer.write(generateRegSuccessHtml());

            return;
        } else {

            resp.sendError(404);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> params = req.getParameterMap();

        String uri = req.getRequestURI();
        String command = uri.substring(uri.lastIndexOf("/") + 1);

        switch (command) {
            case "create":
                if (userService.getByLogin(params.get("Login")[0]) != null) {
                    PrintWriter writer = resp.getWriter();
                    writer.write(generateUserExistsHtml());
                    return;
                } else {

                    Customer customer = new Customer(
                            params.get("Firstname")[0],
                            params.get("Lastname")[0],
                            date.valueOf(params.get("Birthdate")[0]),
                            params.get("Address")[0],
                            params.get("City")[0],
                            params.get("Passport")[0],
                            params.get("Phone")[0]
                    );
                    Long idOfInsertedCustomer = customerService.save(customer);

                    User user = new User(
                            null,
                            params.get("Login")[0],
                            DigestUtils.md5Hex(params.get("Password")[0]).toString().substring(0, 20),
                            params.get("Firstname")[0],
                            "User",
                            idOfInsertedCustomer
                    );
                    userService.save(user);
                    resp.sendRedirect("/Users/reg");
                }
        }
    }

    private String generateUserHtml() {

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>customers</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <a href=\"/\"><img class=\"BackArrow\" src=\"img/arrow.png\" alt=\"home\"></a> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Hello, new user. Tap the button below!</p> " +

                " <button id=\"CreateCustomer\" name=\"customer\">Create an account</button> " +

                " <form action=\"Users/create\" method=\"post\" id=\"HideForm\"> " +

                "<p style=\"color:blue; font-size:120%\">Create your login and password:</p>" +

                " <p><label for=\"Login\">Login</label></p> " +

                " <p><input name=\"Login\" placeholder=\"Login\" type=\"text\" id=\"Login\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Password\">Password</label></p> " +

                " <p><input name=\"Password\" placeholder=\"Password\" type=\"text\" id=\"Password\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                "<p style=\"color:blue; font-size:120%\">Please, fill all fields below to complete the registration.</p>" +

                " <p><label for=\"Firstname\">First Name</label></p> " +

                " <p><input name=\"Firstname\" placeholder=\"Firstname\" type=\"text\" id=\"Firstname\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Lastname\">Last Name</label></p> " +

                " <p><input name=\"Lastname\" placeholder=\"Lastname\" type=\"text\" id=\"Lastname\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Birthdate\">Birthdate</label></p> " +

                " <p><input name=\"Birthdate\" placeholder=\"Birthdate\" type=\"text\" id=\"Birthdate\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Address\">Address</label></p> " +

                " <p><input name=\"Address\" placeholder=\"Address\" type=\"text\" id=\"Address\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"City\">City</label></p> " +

                " <p><input name=\"City\" placeholder=\"City\" type=\"text\" id=\"City\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Passport\">Passport</label></p> " +

                " <p><input name=\"Passport\" placeholder=\"Passport\" type=\"text\" id=\"Passport\" onfocus=\"hideErrorMessageOnFocus(this)\"></p>" +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Phone\">Phone</label></p> " +

                " <p><input name=\"Phone\" placeholder=\"Phone\" type=\"text\" id=\"Phone\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <button type=\"button\" id=\"SaveCustomer\">Save</button> " +

                " </form> " +
                " <p> </p> " +
                " </div> " +
                " </body> " +
                " </html> ";
    }

    private String generateRegSuccessHtml() {

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>customers</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <p> </p> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Registration successful!</p> " +
                " <p class=\"fontBigCapitals\"> You've got a new account!</p> " +
                " <p class=\"fontBigCapitals\"> Use your login and password to enter the account.</p> " +

                " <button type=\"button\" id=\"Go to login screen\" onClick='location.href=\"/\"'>To the login page!</button> " +
                " <p> </p> " +
                " </div> " +
                " </body> " +
                " </html> ";
    }

    static String generateUserExistsHtml() {

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>customers</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <p> </p> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> We are really sorry, but the user with such a login already exists!</p> " +
                " <p class=\"fontBigCapitals\"> Please, use some different data to create a new user.</p> " +

                " <button type=\"button\" id=\"Go to login screen\" onClick='location.href=\"/Users\"'>Create another user!</button> " +
                " <p> </p> " +
                " </div> " +
                " </body> " +
                " </html> ";
    }

    private String generateUserDoesntExistHtml() {

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>customers</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <p> </p> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\">There is no such a user in the database, or you inputted an incorect data!</p> " +

                " <button type=\"button\" id=\"Go to login screen\" onClick='location.href=\"/\"'>Try once more!</button> " +
                " <p> </p> " +
                " </div> " +
                " </body> " +
                " </html> ";
    }
}
