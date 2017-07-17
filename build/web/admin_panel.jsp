<%-- 
    Document   : admin_panel
    Created on : Oct 21, 2015, 5:09:35 PM
    Author     : Deeptha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include  file="res/checkLogin.jsp"%>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="css/styles.css">
        <link type="text/css" rel="stylesheet" href="css/alertify.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/jquery.form.min.js"></script>
        <script type="text/javascript" src="js/alertify.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=false&libraries=places"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home - InsurePlus+</title>
    </head>
    <body id="admin-panel">
        <%@include file="res/admin_header.jsp"%>
        <div class="container" id="main-content"></div>
        <%@include file="res/admin_footer.jsp"%>
    </body>
</html>
