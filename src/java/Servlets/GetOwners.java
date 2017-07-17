package Servlets;

import Controller.Owner;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Deeptha
 */
public class GetOwners extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("search");
            out.write(new Owner().searchOwners(keyword));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}