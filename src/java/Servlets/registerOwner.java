package Servlets;

import Controller.Owner;
import Controller.WebTools;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registerOwner extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            String nic = request.getParameter("nic");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String profile = request.getParameter("profile");
            String password = Controller.WebTools.generatePassword();


            Owner owner = new Controller.Owner();

            if (!owner.isAvailable(nic)) {
                owner.registerOwner(nic, fname, lname, dob, address, city, mobile, email, profile, password, Owner.ONLINE);
                WebTools.sendPassword(email, fname, password);
                out.write("ok");
            } else {
                out.write("exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("error");
        }
    }
}
