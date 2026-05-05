package com.javaquest.servlet;

import com.javaquest.model.Question;
import com.javaquest.service.RoomService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

// JAVA CONCEPT: SERVLET — POST /answer validates player's answer, returns JSON
public class AnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();

        // JAVA CONCEPT: SESSION — read & update game state
        HttpSession session = req.getSession(false);
        if (session == null) { out.print("{\"success\":false}"); return; }

        String qId    = req.getParameter("questionId");
        String answer = req.getParameter("answer").trim().toLowerCase();

        Question q     = RoomService.getQuestion(qId);
        String correct = q.getCorrectAnswer().trim().toLowerCase();

        int score  = (int) session.getAttribute("score");
        int points = q.getType().equals("CODE") ? 20 : 10; // CODE=20, MCQ=10

        @SuppressWarnings("unchecked")
        List<String> answered = (List<String>) session.getAttribute("answeredQuestions");

        boolean isCorrect = answer.equals(correct);

        if (isCorrect && !answered.contains(qId)) {
            answered.add(qId);
            session.setAttribute("answeredQuestions", answered);
            session.setAttribute("score", score + points);
        } else if (!isCorrect) {
            int wrong = (int) session.getAttribute("wrongAttempts");
            session.setAttribute("wrongAttempts", wrong + 1);
            session.setAttribute("score", Math.max(0, score - 5));
        }

        int newScore = (int) session.getAttribute("score");
        String expSafe = q.getExplanation().replace("\\","\\\\").replace("\"","\\\"");

        out.print("{\"correct\":" + isCorrect + ",\"score\":" + newScore
                + ",\"explanation\":\"" + expSafe + "\"}");
    }
}