package com.javaquest;

import com.javaquest.data.QuestionBank;
import com.javaquest.model.Question;
import com.javaquest.model.Room;
import com.javaquest.service.RoomService;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        int totalQuestions = 0;
        System.out.println("=== JavaQuest Backend Verification ===");

        for (Room room : QuestionBank.ALL_ROOMS) {
            int count = room.getQuestions().size();
            totalQuestions += count;
            System.out.println("Room " + room.getRoomId() + ": " + room.getConceptName() + " | Questions: " + count);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Rooms: " + QuestionBank.ALL_ROOMS.size());
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Question Map Size: " + QuestionBank.Q_MAP.size());

        Room room1 = RoomService.getRoom(1);
        System.out.println("\ngetRoom(1): " + (room1 != null ? room1.getConceptName() : "null"));

        Question q = RoomService.getQuestion("R1-Q1");
        System.out.println("getQuestion(\"R1-Q1\"): " + (q != null ? q.getText() : "null"));

        List<String> answered = new ArrayList<>(List.of("R1-Q1", "R1-Q2"));
        List<Question> unanswered = RoomService.getUnanswered(1, answered);
        System.out.println("getUnanswered(1, [R1-Q1, R1-Q2]) count: " + unanswered.size());

        boolean allAnsweredBefore = RoomService.allAnswered(1, answered);
        System.out.println("allAnswered(1, partial): " + allAnsweredBefore);

        answered.addAll(List.of("R1-Q3", "R1-Q4", "R1-Q5"));
        boolean allAnsweredAfter = RoomService.allAnswered(1, answered);
        System.out.println("allAnswered(1, complete): " + allAnsweredAfter);

        boolean expectedStructureOk = QuestionBank.ALL_ROOMS.size() == 18
                && totalQuestions == 90
                && QuestionBank.Q_MAP.size() == 90;
        System.out.println("\nExpected structure (18 rooms, 90 questions): " + expectedStructureOk);
    }
}
