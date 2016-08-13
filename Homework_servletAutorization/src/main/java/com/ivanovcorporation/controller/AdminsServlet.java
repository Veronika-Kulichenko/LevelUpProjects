package com.ivanovcorporation.controller;


import com.ivanovcorporation.domain.User;

import com.ivanovcorporation.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.Map;

public class AdminsServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!req.getRequestURI().equals("/Admins")) {

            resp.sendError(404);
            return;
        }

        PrintWriter writer = resp.getWriter();
        List<User> usersAdmins = userService.listOfUsersByRole("Admin");
        List<User> usersUsers = userService.listOfUsersByRole("User");
        String content = generateRoleAdminHtml(usersAdmins, usersUsers);
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

            case "change":

                User user = userService.get(Long.parseLong(params.get("userid")[0]));
                user.setRole(params.get("role")[0]);

                userService.update(user);

                break;
            case "delete":

                userService.delete(userService.get(Long.parseLong(params.get("userid")[0])));
                break;
        }

        resp.sendRedirect("/Admins");
    }

    private String generateRoleAdminHtml(List<User> admins, List<User> users) {

        StringBuilder sbAdmin = new StringBuilder();
        for (User user : admins)
            sbAdmin.append(createAdminUserRow(user));
        String adminRows = sbAdmin.toString();

        StringBuilder sbUser = new StringBuilder();
        for (User user : users)
            sbUser.append(createAdminUserRow(user));
        String userRows = sbUser.toString();

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>admins</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +

                " </head> " +
                " <body> " +

                " <header> " +
                " <a href=\"/Index\"><img class=\"BackArrow\" src=\"/img/arrow.png\" alt=\"home\"></a> " +


                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Admins</p> " +

                " <table> " +
                " <tr> " +
                " <th>ID</th> " +
                " <th>Login</th> " +
                " <th>Password</th> " +
                " <th>Name</th> " +
                " <th>Role</th> " +
                " <th></th> " +
                " <th></th> " +
                " </tr> " +

                " <tbody id=\"TargetTable\"> " +

                adminRows +

                " </tbody> " +
                " </table> " +

                " <p class=\"fontBigCapitals\"> Users</p> " +

                " <table> " +
                " <tr> " +
                " <th>ID</th> " +
                " <th>Login</th> " +
                " <th>Password</th> " +
                " <th>Name</th> " +
                " <th>Role</th> " +
                " <th></th> " +
                " <th></th> " +
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

    private String createAdminUserRow(User user) {

        String buttonName;
        String assignRole;

        if (user.getRole().equals("Admin")) {
            buttonName = "Disrank";
            assignRole = "User";

        } else {
            buttonName = "Promote";
            assignRole = "Admin";
        }

        return "   <tr> " +
                "      <td>" + user.getUserid() + "</td> " +
                "      <td>" + user.getLogin() + "</td> " +
                "      <td>" + user.getPassword() + "</td> " +
                "      <td>" + user.getName() + "</td> " +
                "      <td>" + user.getRole() + "</td> " +

                "      <td>" +
                (user.getRole().equals("Super") ? "Super" :
                "       <form action=\"Admins/change\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"userid\" value=" + user.getUserid() + ">" +
                "       <input type=\"hidden\" name=\"role\" value=" + assignRole + ">" +

                "       <button type=\"submit\">" +

                buttonName +

                "       </button>" +
                "       </form>") +
                "     </td>" +

                "      <td>" +
                (user.getRole().equals("Super") ? "Super" :
                "       <form action=\"Users/delete\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"userid\" value=\"" + user.getUserid() + "\"/>" +
                "       <button type=\"submit\">Delete</button>" +
                "       </form>") +

                "     </td>" +
                "   </tr> ";
    }
}
