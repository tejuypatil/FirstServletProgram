package com.myfirstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;




import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet( description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {@WebInitParam(name = "user", value = "Teju"),
                @WebInitParam(name = "password", value="BridgeLabz ")}
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (!isValidUserName( user)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User name is in wrong format .</font>");
            rd.include(request, response);
            return;
        }
        String pwd = request.getParameter("pwd");
        if(!isValidPassword(pwd)){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User password is wrong.</font>");
            rd.include(request, response);
            return;
        }
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if (userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);

        }
       else {
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
           PrintWriter out = response.getWriter();
           out.println("<font color=red>Either user name or password is wrong .</font>");
           rd.include(request, response);
        }
    }
    public static boolean isValidUserName(String userName){
        String regex = "[A-Z]{1}[a-z]{2,}";
        Pattern patternChecker = Pattern.compile(regex);
        Matcher matchChecker = patternChecker.matcher(userName);
        if(matchChecker.matches()){
            System.out.println("User name is valid");
            return true;
        }
        else {
            System.out.println("User Name is Invalid");
            return false;
        }
    }
    public static boolean isValidPassword(String userPassword){
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[*.!@#$%^&(){}:;<>?/~_+-=|\\]]).{8,}";
        Pattern patternChecker = Pattern.compile(regex);
        Matcher matchChecker = patternChecker.matcher(userPassword);
        if(matchChecker.matches()){
            System.out.println("Valid Password");
            return true;
        }
        else{
            System.out.println("Invalid Password");
            return false;
        }
    }
}


