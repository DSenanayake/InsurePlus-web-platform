<%-- 
    Document   : index
    Created on : Sep 24, 2015, 7:42:27 PM
    Author     : Deeptha
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="res/login_user.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="css/styles.css">
        <link type="text/css" rel="stylesheet" href="css/alertify.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/alertify.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home - InsurePlus+</title>
    </head>
    <body id="login">
    <center>
        <div class="container well well-sm" style="max-width: 400px;margin-top: 35px">
            <img style="max-width: 200px;padding: 15px" class="img-responsive" src="img/icon.png">
            <!--<h1>Login</h1>-->
            <div class="well well-lg" style="background-color: white">
                <form class="login-form" method="post" action="loginCompany">
                    <table class="login-table">
                        <tr>
                            <td>Username</td>
                            <td><input class="form-control" type="text" name="username"></td>
                        </tr>

                        <tr>
                            <td>Password</td>
                            <td><input class="form-control" type="password" name="password"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="checkbox">
                                    <label for="stay"> <input id="stay" name="stay" type="checkbox"/> Stay signed in.</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right"><button class="btn btn-primary" type="submit">Login</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</body>
</html>
