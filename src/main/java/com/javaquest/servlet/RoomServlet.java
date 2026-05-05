package com.javaquest.servlet;

import com.javaquest.model.Room;
import com.javaquest.service.RoomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
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

        int roomNo = (int) session.getAttribute("currentRoom");

        @SuppressWarnings("unchecked")
        List<String> answered = (List<String>) session.getAttribute("answeredQuestions");

        Room room = RoomService.getRoom(roomNo);

        // Set attributes for room.jsp to display
        req.setAttribute("room",         room);
        req.setAttribute("unanswered",   RoomService.getUnanswered(roomNo, answered));
        req.setAttribute("answeredCount", answered.size());

        // JAVA CONCEPT: SERVLET — RequestDispatcher forwards to JSP (MVC)
        req.getRequestDispatcher("/room.jsp").forward(req, res);
    }
}