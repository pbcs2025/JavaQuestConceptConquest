package com.javaquest.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

// JAVA CONCEPT: SERVLET — GET /gameover calculates rank and forwards to gameover.jsp
public class GameOverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // JAVA CONCEPT: SESSION — read final game state
        HttpSession session = req.getSession(false);
        if (session == null) { res.sendRedirect(req.getContextPath() + "/"); return; }

        int score = (int) session.getAttribute("score");

        // Calculate rank based on score
        String rank, rankMessage;
        if (score >= 900) {
            rank = "Legend";
            rankMessage = "Master of Java. The dungeon is conquered!";
        } else if (score >= 600) {
            rank = "Architect";
            rankMessage = "Solid command of Java. The dungeon bows.";
        } else if (score >= 300) {
            rank = "Developer";
            rankMessage = "You know your Java. The dungeon respects you.";
        } else {
            rank = "Novice";
            rankMessage = "You have entered the dungeon. Keep learning!";
        }

        req.setAttribute("rank",         rank);
        req.setAttribute("rankMessage",  rankMessage);
        req.setAttribute("finalScore",   score);
        req.setAttribute("hintsUsed",    session.getAttribute("hintsUsed"));
        req.setAttribute("wrongAttempts",session.getAttribute("wrongAttempts"));
        req.setAttribute("roomsCompleted",session.getAttribute("roomsCompleted"));

        // JAVA CONCEPT: SERVLET — RequestDispatcher forwards to JSP (MVC)
        req.getRequestDispatcher("/gameover.jsp").forward(req, res);
    }
}