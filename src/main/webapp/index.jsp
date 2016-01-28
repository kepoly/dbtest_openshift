<%-- 
    Document   : newjsp
    Created on : 28-Jan-2016, 1:35:55 PM
    Author     : c0647063
--%>

<%@page import="Sample.SampleCode"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%=SampleCode.getJSON()%>
    </body>
</html>
