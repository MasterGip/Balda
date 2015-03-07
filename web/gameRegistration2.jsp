<%--
  Created by IntelliJ IDEA.
  User: mg
  Date: 14.02.15
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>

   <%try{
       int numberOfPlayers = Integer.parseInt(request.getParameter("number_of_players"));%>
   <form action="/Logic" method="post">
   <%for(int i = 0; i < numberOfPlayers; i++){%>
       Имя игрока <%=i%> :<br>
       <input type="text" name="name_<%=i%>"><br>
       <%}%>
       <input type="hidden" name="number_of_players" value="<%=numberOfPlayers%>">
       <input type="submit" value="Готово">
   </form>
   <%}catch (Exception e){
       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
       if(dispatcher != null){
           dispatcher.forward(request, response);
       }
   }%>

</body>
</html>
