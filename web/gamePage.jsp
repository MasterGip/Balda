<%@ page import="ru.kfu.itis.Logic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.kfu.itis.Gamer" %>
<%--
  Created by IntelliJ IDEA.
  User: mg
  Date: 14.02.15
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%Logic logic = (Logic) session.getAttribute("logic");%>--%>
<%Logic logic = new Logic();
  logic = logic.getLogicFromFile("/home/mg/Загрузки/Balda/web/file.obj");%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="button">
    <a href="NewGame">Новая Игра</a>
    </div>
<br>
<div>
    <div class="element">
<form action="/Add" method="post" autocomplete="off">
    <table>
        <%for(int i = 0; i < 5; i++){%>
        <tr>
            <%for(int j = 0; j < 5; j++){%>
            <td>
                <%=logic.getTextField(i, j)%>
            </td>
            <%}%>
        </tr>
        <%}%>
    </table>
    Введенное слово:<br/>
    <input type="text" name = "word">
    <input type="submit" value = "Готово">
</form>
    </div>
<div class="element">
<%ArrayList<String> playersList = logic.getPlayerList();%>
<table class="game_table">
    <tr>
    <%for(int i = 0; i < playersList.size(); i++){%>
       <td><%=playersList.get(i)%></td>
    <%}%>
    </tr>
    <%for(int i = 0; i < logic.getNumberOfWords(); i++){%>
    <tr>
       <%for(int j = 0; j < playersList.size(); j++){%>
        <td>
            <%=logic.getWord(j, i)%>
        </td>
        <%}%>
    </tr>
    <%}%>
    <tr>
        <%boolean checkForCurrentPlayer = false;
        for(int i = 0; i < playersList.size(); i++){
            if(logic.isPlayerCurrentPlayer(i)){
                checkForCurrentPlayer = true;
            }
            if(!checkForCurrentPlayer){%>
                <td>
                    <%=logic.getWord(i, logic.getNumberOfWords())%>
                </td><%
            }else{%>
                <td>

                </td>
            <%
            }

        }%>
    </tr>
    <tr>
        <%for(int i = 0; i < playersList.size(); i++){%>
        <td>
            <%=logic.getPointsOfPlayer(i)%>
            </td>
        <%}%>
    </tr>
    </div>
    </div>


</table>
</body>
</html>
