package Servlets;

import Controller.Agent;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registerAgent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String nic = request.getParameter("nic");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String dob = request.getParameter("dob");
            String profilePic = request.getParameter("profile");
            int company = 1;
            int status = 1;

            int result = new Agent().registerAgent(nic, company, fname, lname, dob, profilePic, status);
            if (result == Agent.OK) {
                out.write("OK");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
