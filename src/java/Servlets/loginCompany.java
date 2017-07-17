package Servlets;

import Controller.Company;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginCompany", urlPatterns = {"/loginCompany"})
public class loginCompany extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String stay = request.getParameter("stay") != null ? request.getParameter("stay") : "off";

            PrintWriter out = response.getWriter();
            Company company = new Company();
            int login = company.login(username, password);
            if (login != Company.FAILED) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGGED_COMPANY", login);

                System.out.println(stay);
                if (stay.equalsIgnoreCase("on")) {
                    Cookie cookie = new Cookie("STAY_COMPANY", String.valueOf(login));
                    cookie.setMaxAge(604800);
                    response.addCookie(cookie);
                }

                response.sendRedirect("admin_panel.jsp");
            } else {
                out.print("Login Failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
