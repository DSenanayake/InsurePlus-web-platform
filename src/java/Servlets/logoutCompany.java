package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutCompany extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();

            session.removeAttribute("LOGGED_COMPANY");

            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("STAY_COMPANY")) {
                    c.setValue("");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
            
            
            out.write("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
