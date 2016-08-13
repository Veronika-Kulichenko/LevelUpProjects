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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class CustomersServlet extends HttpServlet {

    Date date = new Date(1L);

    private CustomerService customerService = new CustomerService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!req.getRequestURI().equals("/Customers")) {

            resp.sendError(404);
            return;
        }

        PrintWriter writer = resp.getWriter();
        List<Customer> customers = customerService.list();
        String content = generateCustomerHtml(customers);
        writer.write(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> params = req.getParameterMap();

        String uri = req.getRequestURI();
        String command = uri.substring(uri.lastIndexOf("/") + 1);
        HttpSession session = req.getSession();

        switch (command) {

            case "logout":

                session.invalidate();

                break;


            case "create":
                if (userService.getByLogin(params.get("Login")[0]) != null) {
                    PrintWriter writer = resp.getWriter();
                    writer.write(UsersServlet.generateUserExistsHtml());
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

                }
                break;

            case "delete":

                userService.delete(userService.getByCustomerID(Long.parseLong(params.get("id")[0])));
                break;
        }
        resp.sendRedirect("/Customers");
    }

    private String generateCustomerHtml(List<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customers)
            sb.append(createCustomerRow(customer));
        String userRows = sb.toString();

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
                " <a href=\"/Index\"><img class=\"HomePicture\" src=\"/img/homepage_house_home_building.png\" alt=\"home\"></a> " +
                " <ul> " +
                " <li style=\"background:green; color:white\">Customers</li> " +
                " <li><a href=\"/Accounts\">Accounts</a></li> " +
                " <li><a href=\"/Transactions\">Transactions</a></li> " +
                " </ul> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Customers</p> " +


                " <button id=\"CreateCustomer\" name=\"customer\">Create an account</button> " +

                " <form action=\"Customers/create\" method=\"post\" id=\"HideForm\"> " +

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

                " <table> " +
                " <tr> " +
                " <th>Firstname</th> " +
                " <th>Lastname</th> " +
                " <th>Birthdate</th> " +
                " <th>Address</th> " +
                " <th>City</th> " +
                " <th>Passport</th> " +
                " <th>Phone</th> " +
                " <th> </th> " +
                " </tr> " +

                " <tbody id=\"TargetTable\"> " +

                userRows +

                " </tbody> " +
                " </table> " +

                "       <form action=\"Customers/logout\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"id\" value=\"logout\"/>" +
                "       <button type=\"submit\">Logout</button>" +
                "       </form>" +
                " <p> </p> " +
                " </div> " +
                " </body> " +
                " </html> ";
    }

    private String createCustomerRow(Customer customer) {
        if ((userService.getByCustomerID(customer.getId()).getRole().equals("Super"))) {
            return "";
        }else{
            return "   <tr> " +
                    "      <td><a href=\"/Accounts/User?id=" + customer.getId() + "\"" + " style=\"color:green\">" + customer.getFirstname() + "</a></td> " +
                    "      <td>" + customer.getLastname() + "</td> " +
                    "      <td>" + customer.getBirthdate() + "</td> " +
                    "      <td>" + customer.getAddress() + "</td> " +
                    "      <td>" + customer.getCity() + "</td> " +
                    "      <td>" + customer.getPassport() + "</td> " +
                    "      <td>" + customer.getPhone() + "</td> " +
                    "      <td>" +
                    "       <form action=\"Customers/delete\" method=\"post\">" +
                    "       <input type=\"hidden\" name=\"id\" value=\"" + customer.getId() + "\"/>" +
                    "       <button type=\"submit\">Delete</button>" +
                    "       </form>" +
                    "     </td>" +
                    "   </tr> ";
        }
    }
}
