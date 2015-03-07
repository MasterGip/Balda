package ru.kfu.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mg on 15.02.15.
 */
public class NumberOfPlayersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        try {
//            System.out.println("!!!");
            int numberOfPlayers = Integer.parseInt(req.getParameter("number_of_players"));
//            System.out.println("!!");
            if (numberOfPlayers < 2 || numberOfPlayers > 10){
                throw new Exception();
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("gameRegistration2.jsp");
            req.setAttribute("number_of_players", numberOfPlayers);
            if(dispatcher!=null){
                dispatcher.forward(req, resp);
            }
        }catch (Exception e){
            e.printStackTrace();
            pw.write("Число игроков должно быть числом<br>" +
                    " <a href=\"index.jsp\"> Назад </a>");
            pw.close();
        }
    }
}
