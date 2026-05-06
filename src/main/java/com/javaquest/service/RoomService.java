package com.javaquest.service;

import com.javaquest.data.QuestionBank;
import com.javaquest.model.*;
import java.util.*;

public final class RoomService {

    private RoomService() {
    }

    public static Room getRoom(int id) {
        // JAVA CONCEPT: COLLECTIONS
        // ArrayList stream search
        return QuestionBank.ALL_ROOMS.stream()
                .filter(room -> room.getRoomId() == id)
                .findFirst()
                .orElse(null);
    }

    public static Question getQuestion(String id) {
        // JAVA CONCEPT: COLLECTIONS
        // HashMap O(1) lookup
        return QuestionBank.Q_MAP.get(id);
    }

    public static List<Question> getUnanswered(int roomId, List<String> answeredIds) {
        Room room = getRoom(roomId);
        if (room == null) {
            return List.of();
        }
        List<String> safeAnswered = answeredIds == null ? List.of() : answeredIds;

        // JAVA CONCEPT: LAMBDA
        // stream filter with anonymous Predicate<Question>
        return room.getQuestions().stream()
                .filter(q -> !safeAnswered.contains(q.getId()))
                .toList();
    }

    public static boolean allAnswered(int roomId, List<String> answeredIds) {
        Room room = getRoom(roomId);
        if (room == null) {
            return false;
        }
        List<String> safeAnswered = answeredIds == null ? List.of() : answeredIds;
        return room.getQuestions().stream()
                .allMatch(q -> safeAnswered.contains(q.getId()));
    }
}
