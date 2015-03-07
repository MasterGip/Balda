<%@ page import="ru.kfu.itis.Logic" %>
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
    <title>Добро пожаловать!</title>
    <link type="text/css" href="style.css" rel="stylesheet">
</head>
<body>
<% Logic logic = new Logic();
    logic = logic.getLogicFromFile("/home/mg/Загрузки/Balda/web/file.obj");
//    boolean newGame = false;
//    try {
//        newGame = (Boolean) request.getAttribute("newgame");
//    }catch (Exception e){
//
//    }
    if(logic != null && !logic.isFieldFull()){
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("gamePage.jsp");
    if(requestDispatcher != null){
        requestDispatcher.forward(request, response);
    }
}%>
<h1>Добро пожаловать!</h1>
<h2>Выберите тип игры:</h2>
<ul>

    <li><a href="gameRegistration1.jsp">На одном компьютере</a></li>
    <li><a href="">На нескольких комппьютерах</a></li>
</ul>
</body>
</html>
