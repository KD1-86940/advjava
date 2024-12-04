package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("call to doPost()");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (UserDao userDao = new UserDaoImpl()) {
            User dbuser = userDao.findByEmail(email);
            System.out.println(dbuser);

            if (dbuser != null && dbuser.getPassword().equals(password)) {
                Cookie c1 = new Cookie("uname", dbuser.getFirstName());
                c1.setMaxAge(3600);
                resp.addCookie(c1);
                
                Cookie c2 = new Cookie("role", dbuser.getRole());
                c2.setMaxAge(3600);
                resp.addCookie(c2);

                System.out.println(dbuser.getRole());
                if (dbuser.getRole().equals("admin"))
                    resp.sendRedirect("result");
                else
                    resp.sendRedirect("candlist");
            } else {
                resp.setContentType("text/html");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter out = resp.getWriter();

                out.printf("<html>");
                out.printf("<head>");
                out.printf("<title>Voting Application</title>");
                out.printf("<style>");
                out.printf("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 20px; }");
                out.printf(".container { max-width: 400px; margin: 50px auto; background: #fff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px; text-align: center; }");
                out.printf("h2 { color: #e74c3c; }");
                out.printf("p { color: #555; }");
                out.printf("a { color: #007BFF; text-decoration: none; }");
                out.printf("a:hover { text-decoration: underline; }");
                out.printf("</style>");
                out.printf("</head>");
                out.printf("<body>");
                out.printf("<div class='container'>");
                out.printf("<h2>Login Failed</h2>");
                out.printf("<p>Sorry, invalid email or password.</p>");
                out.printf("<p><a href='index.html'>Login Again</a></p>");
                out.printf("</div>");
                out.printf("</body>");
                out.printf("</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
