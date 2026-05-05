package com.javaquest.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

// JAVA CONCEPT: SERVLET — handles the welcome form POST
public class StartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("playerName");
        if (name == null || name.trim().isEmpty()) name = "Developer";

        // JAVA CONCEPT: SESSION — invalidate old session, create a fresh one
        HttpSession session = req.getSession(true);
        session.invalidate();
        session = req.getSession(true);

        session.setAttribute("playerName",        name.trim());
        session.setAttribute("currentRoom",       1);
        session.setAttribute("score",             0);
        session.setAttribute("hintsUsed",         0);
        session.setAttribute("wrongAttempts",     0);
        session.setAttribute("roomsCompleted",    0);

        // JAVA CONCEPT: COLLECTIONS — ArrayList stored in session
        session.setAttribute("answeredQuestions", new ArrayList<String>());

        res.sendRedirect(req.getContextPath() + "/room");
    }
}