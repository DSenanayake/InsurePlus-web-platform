package Servlets;

import Controller.Company;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class checkSession extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("STAY_COMPANY")) {
                    int login = new Company().login(Integer.parseInt(cookie.getValue()));
                    if (login != Company.FAILED) {
                        request.setAttribute("LOGGED_COMPANY", login);
                    }
                    break;
                }
            }

            if (request.getAttribute("LOGGED_COMPANY") != null) {
                out.write("FORWARD");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
