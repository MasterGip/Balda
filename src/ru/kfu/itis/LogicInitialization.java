package ru.kfu.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by mg on 14.02.15.
 */
public class LogicInitialization extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        ArrayList<String> names = new ArrayList<String>();
        int numberOfPlayers = Integer.parseInt(req.getParameter("number_of_players"));
        HttpSession session = req.getSession();

        if(session.getAttribute("logic")!=null){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("gamePage.jsp");
            if(requestDispatcher != null){

                requestDispatcher.forward(req, resp);
            }
        }else {

            try {
                if (numberOfPlayers < 2 || numberOfPlayers > 10) {
                    throw new InputCharacterException();
                }
                for (int i = 0; i < numberOfPlayers; i++) {
//                System.out.println(req.getParameter("name_" + i));
                    names.add(req.getParameter("name_" + i));
                }
                Logic logic = new Logic(names);



                logic.saveLogic("/home/mg/Загрузки/Balda/web/file.obj");

                session.setAttribute("logic", logic);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("gamePage.jsp");
                if (requestDispatcher != null) {
                    requestDispatcher.forward(req, resp);
                }
            } catch (InputCharacterException e) {
                e.printStackTrace();
                resp.setContentType("text/html");
                resp.setCharacterEncoding("utf-8");
                PrintWriter pw = resp.getWriter();
                pw.write("Игроки должны называться по разному!");
            }
        }

    }
}
