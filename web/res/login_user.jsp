<%-- 
    Document   : login_user
    Created on : Nov 10, 2015, 12:07:05 PM
    Author     : Deeptha
--%>

<%@page import="Controller.Company"%>
<%
    try {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("STAY_COMPANY")) {
                int login = new Company().login(Integer.parseInt(cookie.getValue()));
                if (login != Company.FAILED) {
                    request.getSession().setAttribute("LOGGED_COMPANY", login);
                }
            }
        }
        if (request.getSession().getAttribute("LOGGED_COMPANY") != null) {
            response.sendRedirect("admin_panel.jsp");
        }

    } catch (Exception e) {
    }
%>