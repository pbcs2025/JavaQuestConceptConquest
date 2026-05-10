package com.javaquest.servlet;

import com.javaquest.model.Room;
import com.javaquest.model.Question;
import com.javaquest.service.RoomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

// JAVA CONCEPT: SERVLET — GET /room loads the current room
public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // JAVA CONCEPT: SESSION — read game state
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Object currentRoomObj = session.getAttribute("currentRoom");
        if (!(currentRoomObj instanceof Integer)) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }
        int roomNo = (int) currentRoomObj;

        @SuppressWarnings("unchecked")
        List<String> answered = (List<String>) session.getAttribute("answeredQuestions");
        if (answered == null) {
            answered = Collections.emptyList();
        }

        Room room = RoomService.getRoom(roomNo);
        List<Question> unanswered = RoomService.getUnanswered(roomNo, answered);
        Question currentQuestion = unanswered.isEmpty() ? null : unanswered.get(0);

        // Set attributes for room.jsp to display
        req.setAttribute("room",         room);
        req.setAttribute("unanswered",   unanswered);
        req.setAttribute("currentQuestion", currentQuestion);
        req.setAttribute("answeredCount", answered.size());

        // JAVA CONCEPT: SERVLET — RequestDispatcher forwards to JSP (MVC)
        req.getRequestDispatcher("/room.jsp").forward(req, res);
    }
}