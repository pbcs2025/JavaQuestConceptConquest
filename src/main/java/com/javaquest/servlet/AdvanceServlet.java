package com.javaquest.servlet;

import com.javaquest.service.RoomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

// JAVA CONCEPT: SERVLET — POST /advance moves player to next room
public class AdvanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // JAVA CONCEPT: SESSION — read game state
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int roomNo = (int) session.getAttribute("currentRoom");
        int score  = (int) session.getAttribute("score");

        @SuppressWarnings("unchecked")
        List<String> answered = (List<String>) session.getAttribute("answeredQuestions");

        // Guard: must answer all 5 questions before advancing
        if (!RoomService.allAnswered(roomNo, answered)) {
            session.setAttribute("advanceError", "Answer all 5 questions first!");
            res.sendRedirect(req.getContextPath() + "/room");
            return;
        }

        // Award room completion bonus (+15 pts)
        session.setAttribute("score", score + 15);
        int rooms = (int) session.getAttribute("roomsCompleted");
        session.setAttribute("roomsCompleted", rooms + 1);

        if (roomNo == 18) {
            res.sendRedirect(req.getContextPath() + "/gameover");
        } else {
            session.setAttribute("currentRoom", roomNo + 1);
            res.sendRedirect(req.getContextPath() + "/room");
        }
    }
}