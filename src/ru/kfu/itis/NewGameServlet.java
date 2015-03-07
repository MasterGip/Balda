package ru.kfu.itis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mg on 15.02.15.
 */
public class NewGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        req.setAttribute("newgame", true);

        if(session.getAttribute("logic")!=null) {
            session.removeAttribute("logic");
        }
        Logic logic = new Logic();
        logic.deleteLogic("/home/mg/Загрузки/Balda/web/file.obj");
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        if(dispatcher != null){
            dispatcher.forward(req, resp);
        }
    }
}
