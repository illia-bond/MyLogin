package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;
import services.User;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            if (request.getParameter("logout") == null) {
                response.sendRedirect("./home");
            } else {
                session.invalidate();
                request.setAttribute("message", "Log out successful!");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("pass");
        AccountService as;
        User user = null;
        if (username == null || password == null || username.equals("") || password.equals("")) {
            request.setAttribute("message", "Please fill in all fields");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
           as = new AccountService();
           user = as.login(username, password);
           
           if (user == null) {
                request.setAttribute("message", "Username or Password are invalid. Please try again");
                request.setAttribute("username", username);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
           } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect("./home");
           }
        }   
    }
}
