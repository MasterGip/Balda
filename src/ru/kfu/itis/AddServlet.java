package ru.kfu.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by mg on 15.02.15.
 */
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
//        Logic logic = (Logic) session.getAttribute("logic");
        Logic logic = new Logic();
        logic = logic.getLogicFromFile("/home/mg/Загрузки/Balda/web/file.obj");
//        System.out.println("!");
        int numberOfChangedCharacters = 0;
        int x = -1;
        int y = -1;
        char sym = 'e';
        for(int i = 0; i < 5 && numberOfChangedCharacters < 2; i++){
            for(int j = 0; j < 5 && numberOfChangedCharacters < 2; j++){

                String current = req.getParameter("field_" + i + "_" + j);
//                System.out.println(logic.getCharacter(i, j) + "$" + current);
                if(current!=null && !current.equals("")
                        && !current.equals(" ")
                        &&!logic.getCharacter(i, j).equals(current)){
                    numberOfChangedCharacters++;
                    x = i;
                    y = j;

                    sym = current.charAt(0);
                }
            }
        }
//        System.out.println(numberOfChangedCharacters + " " + sym);
        boolean error = true;
        if(numberOfChangedCharacters == 1){
            String word = req.getParameter("word");
            word = word.toUpperCase();
            if(word.matches("[А-Я]*")){
//                System.out.println("the best");
                error = !logic.addCharacter(x, y, sym + "", word);
            }
        }
        if(!error){
            logic.saveLogic("/home/mg/Загрузки/Balda/web/file.obj");
            logic.saveLogic("/home/mg/Загрузки/Balda/web/file.obj");

            if(!logic.isFieldFull()) {


                RequestDispatcher dispatcher = req.getRequestDispatcher("gamePage.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            }else{
                logic.sortPlayers();
                PrintWriter pw = resp.getWriter();

                ArrayList<String> listOfPlayers = logic.getPlayerList();
                pw.write("<link rel=\"stylesheet\" href=\"style.css\">");
                pw.write("<ol>");
                for(int i = 0; i < logic.getPlayerList().size(); i++){
                    pw.write("<li>" + listOfPlayers.get(i) + " - " + logic.getPointsOfPlayer(i) + " очков</li>");
                }
                pw.write("</ol>");
                pw.write("<div class=\"button\"><a href=\"NewGame\">Новая Игра</a></div>");
                pw.close();
                session.removeAttribute("logic");
            }
        }else{
            PrintWriter pw = resp.getWriter();
            pw.write("Ошибка!<br><a href=\"gamePage.jsp\">Назад</a>");
            pw.close();

        }
    }
}
