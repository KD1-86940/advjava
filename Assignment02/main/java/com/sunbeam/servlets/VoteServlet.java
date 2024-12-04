package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String candId = req.getParameter("candidate");
        int id = Integer.parseInt(candId);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Vote</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
        out.println(".container { width: 80%; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
        out.println("h2 { color: #333; font-size: 24px; }");
        out.println("h4 { color: green; font-size: 20px; }");
        out.println("a { text-decoration: none; color: #0066cc; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("hr { margin: 20px 0; border: 0; border-top: 1px solid #ccc; }");
        out.println(".message { font-size: 18px; margin-bottom: 20px; }");
        out.println(".footer { text-align: center; padding: 10px; background-color: #f4f4f9; border-top: 1px solid #ddd; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");

        Cookie[] arr = req.getCookies();
        String userName = "", role = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("uname")) {
                    userName = c.getValue();
                }
                if (c.getName().equals("role")) {
                    role = c.getValue();
                }
            }
        }

        out.printf("<p>Hello, <strong>%s</strong> (%s)</p>\n", userName, role);

        out.println("<h2>Voting Status</h2>");
        
        try (CandidateDao candDao = new CandidateDaoImpl()) {
            int count = candDao.incrVote(id);
            if (count == 1) {
                out.println("<h4>You have successfully casted your vote.</h4>");
            } else {
                out.println("<h4>Your voting failed. Please try again.</h4>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        out.println("<p class='message'><a href='logout'>Sign Out</a></p>");
        
        out.println("</div>");
        
        out.println("<div class='footer'>");
        out.println("<p>&copy; Voting Application | All Rights Reserved</p>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}

