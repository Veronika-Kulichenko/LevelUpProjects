package com.ivanovcorporation.controller;

import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.Transaction;
import com.ivanovcorporation.service.AccountService;
import com.ivanovcorporation.service.CustomerService;
import com.ivanovcorporation.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionsServlet extends HttpServlet {

    private static String redirect;

    private static Long currentCustomerID;
    private  static Long currentAccountID;
    private static String role;


    private TransactionService transactionService = new TransactionService();
    private AccountService accountService = new AccountService();
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        role = (String) session.getAttribute("role");
        Long sessionID = (Long) session.getAttribute("customerID");


        if (!req.getRequestURI().equals("/Transactions") && !req.getRequestURI().equals("/Transactions/User")) {

            resp.sendError(404);
            return;
        }
        PrintWriter writer = resp.getWriter();
        String content;

        if (req.getParameter("accountnumber") != null) {
            currentAccountID = Long.parseLong(req.getParameter("accountnumber"));

            if (!accountService.get(currentAccountID).getCustomerid().equals(sessionID)&& role.equals("User")) {
                resp.sendRedirect("/Index");
                return;
            }

            List<Transaction> transactions = transactionService.listOfTransactionsByAccount(currentAccountID);
            content = generateTransactionsByAccountHtml(transactions, req);
            redirect = "/Transactions/User?accountnumber=" + currentAccountID;

        } else {
            if (req.getParameter("id") != null) {

                currentCustomerID = Long.parseLong(req.getParameter("id"));

                if (!currentCustomerID.equals(sessionID)&&role.equals("User")) {
                    resp.sendRedirect("/Index");
                    return;
                }

                List<Account> allClientAccounts = accountService.listOfAccountsByCustomer(currentCustomerID);
                List<Transaction> allTransactionsByClient = new ArrayList<>();

                for (Account account : allClientAccounts) {
                    allTransactionsByClient.addAll(transactionService.listOfTransactionsByAccount(account.getAccountnumber()));
                }

                content = generateTransactionsByUser(allTransactionsByClient,req);
                redirect = "/Transactions/User?id=" + currentCustomerID;

            } else {

                List<Transaction> transactions = transactionService.list();
                content = generateTransactionHtml(transactions);
                redirect = "/Transactions";
            }
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
                Transaction transaction = new Transaction(
                        new BigDecimal(params.get("Amount")[0]),
                        params.get("Operationtype")[0],
                        currentAccountID
                );
                transactionService.save(transaction);

                break;
            case "delete":

                transactionService.delete(transactionService.get(Long.parseLong(params.get("id")[0])));
                break;
        }
        resp.sendRedirect(redirect);
    }

    private String generateTransactionHtml(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions)
            sb.append(createTransactionRow(transaction));
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
                " <a href=\"/Index\"><img class=\"HomePicture\" src=\"img/homepage_house_home_building.png\" alt=\"home\"></a> " +
                " <ul> " +
                " <li><a href=\"/Customers\">Customers</a></li> " +
                " <li><a href=\"/Accounts\">Accounts</a></li> " +
                " <li style=\"background:green; color:white\"><a href=\"/Transactions\">Transactions</a></li> " +
                " </ul> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> All existing transactions</p> " +

                " <table> " +
                " <tr> " +
                " <th>ID</th> " +
                " <th>Amount</th> " +
                " <th>Date</th> " +
                " <th>Operation type</th> " +
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

    private String generateTransactionsByAccountHtml(List<Transaction> transactions, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions)
            sb.append(createTransactionRow(transaction));
        String userRows = sb.toString();

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>transactions</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +

                (role.equals("User") ? " <a href=\"/Accounts/User?id="
                        + accountService.get(Long.parseLong(req.getParameter("accountnumber"))).getCustomerid()
                        + "\"><img class=\"BackArrow\" src=\"../img/arrow.png\" alt=\"home\"></a> "
                        + "\">Accounts</a></li>"
                        : " <a href=\"/Accounts"
                        + "\"><img class=\"BackArrow\" src=\"../img/arrow.png\" alt=\"home\"></a> "
                        + "\">Accounts</a></li>") +

                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> Transactions of account number" + " " +
                req.getParameter("accountnumber") +
                " " + "of customer " +
                customerService.get(accountService.get(Long.parseLong(req.getParameter("accountnumber"))).getCustomerid()).getFirstname() + " " +
                customerService.get(accountService.get(Long.parseLong(req.getParameter("accountnumber"))).getCustomerid()).getLastname() + " " +
                "with id = " +
                accountService.get(Long.parseLong(req.getParameter("accountnumber"))).getCustomerid() + "</p> " +

                " <button id=\"CreateCustomer\" name=\"customer\">Create transaction</button> " +

                " <form action=\"Transactions/create\" method=\"post\" id=\"HideForm\"> " +

                " <p><label for=\"Amount\">Amount</label></p> " +

                " <p><input name=\"Amount\" placeholder=\"Amount\" type=\"text\" id=\"Amount\" onfocus=\"hideErrorMessageOnFocus(this)\"></p> " +
                " <p><span>This field can't be empty!</span></p> " +

                " <p><label for=\"Operationtype\">Operation type</label></p> " +


                " <p><input style=\"align-left; width:25px;\" type=\"radio\" name=\"Operationtype\" value=\"PUT\"  id=\"put\" checked>PUT</p> " +


                " <p><input style=\"align-left; width:25px;\" type=\"radio\" name=\"Operationtype\" value=\"WSD\"  id=\"withdraw \">WITHDRAW</p> " +

                " " +

                " <button type=\"button\" id=\"SaveCustomer\">Save</button> " +

                " </form> " +

                " <table> " +
                " <tr> " +
                " <th>ID</th> " +
                " <th>Amount</th> " +
                " <th>Date</th> " +
                " <th>Operation type</th> " +
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

    private String generateTransactionsByUser(List<Transaction> transactions, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions)
            sb.append(createTransactionRow(transaction));
        String userRows = sb.toString();

        return "<!DOCTYPE html> " +
                "<html> " +
                "<head> " +
                " <meta charset=\"utf-8\"> " +
                " <title>transactions</title> " +
                " <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/style.css\"> " +
                " <script src=\"/javascript/script.js\"></script> " +
                " </head> " +
                " <body> " +

                " <header> " +
                " <a href=\"/Accounts\"><img class=\"BackArrow\" src=\"../img/arrow.png\" alt=\"home\"></a> " +
                " </header> " +

                " <div class=\"mainContentBackground\"> " +

                " <p class=\"fontBigCapitals\"> All Transactions of customer " +
                customerService.get(Long.parseLong(req.getParameter("id"))).getFirstname() + " " +
                customerService.get(Long.parseLong(req.getParameter("id"))).getLastname() + " " +
                "with id = " +
                Long.parseLong(req.getParameter("id")) + "</p> " +

                " <table> " +
                " <tr> " +
                " <th>ID</th> " +
                " <th>Amount</th> " +
                " <th>Date</th> " +
                " <th>Operation type</th> " +
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

    private String createTransactionRow(Transaction transaction) {
        return "   <tr> " +
                "      <td>" + transaction.getId() + "</td> " +
                "      <td>" + transaction.getAmount() + "</td> " +
                "      <td>" + transaction.getDate() + "</td> " +
                "      <td>" + transaction.getOperationtype() + "</td> " +
                "      <td>" +
                "       <form action=\"Transactions/delete\" method=\"post\">" +
                "       <input type=\"hidden\" name=\"id\" value=\"" + transaction.getId() + "\"/>" +
                "       <button type=\"submit\">Delete</button>" +
                "       </form>" +
                "     </td>" +
                "   </tr> ";
    }
}
