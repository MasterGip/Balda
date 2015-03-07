<%--
  Created by IntelliJ IDEA.
  User: mg
  Date: 14.02.15
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
<%if(session.getAttribute("logic")!=null){
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("gamePage.jsp");
    if(requestDispatcher != null){
        requestDispatcher.forward(request, response);
    }
}%>
<form action="/Number" method="post">
    Введите количество игроков:<br/>
    <input type="text" name="number_of_players">
    <input type="submit" value="Далее">
</form>
</body>
</html>
