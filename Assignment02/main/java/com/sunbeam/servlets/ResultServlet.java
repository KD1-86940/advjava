package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.entities.Candidate;
@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("call to doPost()");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Candidate> list = new ArrayList<>();
        try (CandidateDao canDao = new CandidateDaoImpl()) {
            list = canDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 20px; }");
        out.println("table { width: 80%; border-collapse: collapse; margin: 20px auto; background-color: #fff; }");
        out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("a { text-decoration: none; color: #007bff; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("h2 { text-align: center; color: #333; }");
        out.println("hr { margin: 20px auto; width: 80%; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        Cookie[] arr = req.getCookies();
        String userName = "", role = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("uname"))
                    userName = c.getValue();
                if (c.getName().equals("role"))
                    role = c.getValue();
            }
        }

        out.printf("<h2>Welcome, %s (%s)</h2><hr/>\n", userName, role);

        out.println("<h2>Voting Result</h2>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Name</th>");
        out.println("<th>Party</th>");
        out.println("<th>Votes</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        for (Candidate c : list) {
            out.println("<tr>");
            out.printf("<td>%d</td>", c.getId());
            out.printf("<td>%s</td>", c.getName());
            out.printf("<td>%s</td>", c.getParty());
            out.printf("<td>%d</td>", c.getVotes());
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        // Logout link
        out.println("<p style='text-align: center;'><a href='logout'>Sign Out</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}
