<%-- 
    Document   : checkLogin
    Created on : Nov 8, 2015, 10:34:03 PM
    Author     : Deeptha
--%>

<%
    HttpSession ses = request.getSession();
    Object ATTR = ses.getAttribute("LOGGED_COMPANY");
    if (ATTR == null) {
        response.sendRedirect("index.jsp");
    }
%>