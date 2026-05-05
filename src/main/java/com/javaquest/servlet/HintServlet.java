package com.javaquest.servlet;

import com.javaquest.model.Question;
import com.javaquest.service.RoomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

// JAVA CONCEPT: SERVLET — POST /hint returns explanation and deducts 5 pts
public class HintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        // JAVA CONCEPT: SESSION — read & update game state
        HttpSession session = req.getSession(false);
        if (session == null) { out.print("{\"success\":false}"); return; }

        String qId = req.getParameter("questionId");
        Question q = RoomService.getQuestion(qId);

        int score = (int) session.getAttribute("score");
        int hints = (int) session.getAttribute("hintsUsed");

        session.setAttribute("score",     Math.max(0, score - 5));
        session.setAttribute("hintsUsed", hints + 1);

        int newScore = (int) session.getAttribute("score");
        String expSafe = q.getExplanation().replace("\\","\\\\").replace("\"","\\\"");

        out.print("{\"explanation\":\"" + expSafe + "\",\"score\":" + newScore + "}");
    }
}