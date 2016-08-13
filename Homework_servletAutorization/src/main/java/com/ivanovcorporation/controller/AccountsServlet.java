package com.ivanovcorporation.controller;

import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.service.AccountService;
import com.ivanovcorporation.service.CustomerService;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AccountsServlet extends HttpServlet {

    private static String redirect;

    private static Long currentCustomerID;


    private AccountService accountService = new AccountService();
    private CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Long sessionID = (Long) session.getAttribute("customerID");
        String role = (String) session.getAttribute("role");



        if (!req.getRequestURI().equals("/Accounts") && !req.getRequestURI().equals("/Accounts/User")) {

            resp.sendError(404);
            return;
        }
        PrintWriter writer = resp.getWriter();
        String content;

        if (req.getParameter("id") != null) {

            currentCustomerID = Long.parseLong(req.getParameter("id"));

            if (!currentCustomerID.equals(sessionID) && role.equals("User")) {
                resp.sendRedirect("/Index");
                return;
            }

            List<Account> accounts = accountService.listOfAccountsByCustomer(currentCustomerID);
            content = generateAccountByCustomerHtml(accounts, req);

            redirect = "/Accounts/User?id=" + currentCustomerID;
        } else {

            List<Account> accounts = accountService.list();
            content = generateAccountHtml(accounts);
            redirect = "/Accounts";
        }
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
                Account account = new Account(
                        new BigDecimal(params.get("Balance")[0]),
                        params.get("Currency")[0],
                        Boolean.valueOf(params.get("Blocked")[0]),
                        currentCustomerID
                );
                accountService.save(account);

                break;
            case "delete":

                accountService.delete(accountService.get(Long.parseLong(req.getParameter("accountnumber"))));
                break;
        }

        resp.sendRedirect(redirect);
    }

    private String generateAccountHtml(List<Account> accounts) {
        StringBuilder sb = new StringBuilder();
        for (Account account : accounts)
            sb.append(createAccountRow(account));
        String userRows = sb.toString();

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>customers</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +

                " </head> " +
                " <body> " +

                " <header> " +
                " <a href=\"/Index\"><img class=\"HomePicture\" src=\"/img/homepage_house_home_building.png\" alt=\"home\"></a> " +
                " <ul> " +
                " <li><a href=\"/Customers\">Customers</a></li> " +
                " <li style=\"background:green; color:white\"><a href=\"/Accounts\">Accounts</a></li> " +
                " <li><a href=\"/Transactions\">Transactions</a></li> " +
                " </ul> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> All existing accounts</p> " +

                " <table> " +
                " <tr> " +
                " <th>Acoount number</th> " +
                " <th>Balance</th> " +
                " <th>Date of creation</th> " +
                " <th>Currency</th> " +
                " <th>Blocked</th> " +
                " <th> </th> " +
                " </tr> " +

                " <tbody id=\"TargetTable\"> " +

                userRows +

                " </tbody> " +
                " </table> " +


                "       <form action=\"Accounts/logout\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"id\" value=\"logout\"/>" +
                "       <button type=\"submit\">Logout</button>" +
                "       </form>" +
                " <p> </p> " +

                " </div> " +
                " </body> " +
                " </html> ";
    }

    private String generateAccountByCustomerHtml(List<Account> accounts, HttpServletRequest req) {

        StringBuilder sb = new StringBuilder();
        for (Account account : accounts)
            sb.append(createAccountRow(account));
        String userRows = sb.toString();

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>accounts</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <a href=\"/Customers\"><img class=\"BackArrow\" src=\"../img/arrow.png\" alt=\"home\"></a> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Accounts of" + " " +
                customerService.get(Long.parseLong(req.getParameter("id"))).getFirstname() + " " +
                customerService.get(Long.parseLong(req.getParameter("id"))).getLastname() + " " +
                "with id = " + " " +
                req.getParameter("id") + "</p> " +


                " <button id=\"CreateCustomer\" name=\"customer\">Create account</button> " +

                " <form action=\"Accounts/create\" method=\"post\" id=\"HideForm\"> " +

                " <p><label for=\"Balance\">Balance</label></p> " +

                " <p><input name=\"Balance\" placeholder=\"Balance\" type=\"text\" id=\"Balance\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Currency\">Currency</label></p> " +

                " <p><input name=\"Currency\" placeholder=\"Currency\" type=\"text\" id=\"Currency\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +


                " <p><label>Account status</label></p> " +

                " <p><input style=\"align-left; width:25px;\" type=\"radio\" name=\"blocked\" value=\"true\"  id=\"BlockedTrue\">Blocked</p> " +


                " <p><input style=\"align-left; width:25px;\" type=\"radio\" name=\"Blocked\" value=\"false\"  id=\"blockedFalse \" checked>Unblocked</p> " +

                " " +

                " <button type=\"button\" id=\"SaveCustomer\">Save</button> " +

                " </form> " +

                " <table> " +
                " <tr> " +
                " <th>Acoount number</th> " +
                " <th>Balance</th> " +
                " <th>Date of creation</th> " +
                " <th>Currency</th> " +
                " <th>Blocked</th> " +
                " <th> </th> " +
                " </tr> " +

                " <tbody id=\"TargetTable\"> " +

                userRows +

                " </tbody> " +
                " </table> " +


                "       <form action=\"Accounts/logout\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"id\" value=\"logout\"/>" +
                "       <button type=\"submit\">Logout</button>" +
                "       </form>" +
                " <p> </p> " +

                " </div> " +
                " </body> " +
                " </html> ";
    }

    private String createAccountRow(Account account) {
        return "   <tr> " +
                "      <td><a href=\"/Transactions/User?accountnumber=" + account.getAccountnumber() + "&id=" + account.getCustomerid() + "\"" + " style=\"color:green\">" + account.getAccountnumber() + "</a></td> " +
                "      <td>" + account.getBalance() + "</td> " +
                "      <td>" + account.getCreationdate() + "</td> " +
                "      <td>" + account.getCurrency() + "</td> " +
                "      <td>" + account.getBlocked() + "</td> " +
                "      <td>" +
                "       <form action=\"Accounts/delete\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"accountnumber\" value=\"" + account.getAccountnumber() + "\"/>" +
                "       <button type=\"submit\">Delete</button>" +
                "       </form>" +
                "     </td>" +
                "   </tr> ";
    }
}
