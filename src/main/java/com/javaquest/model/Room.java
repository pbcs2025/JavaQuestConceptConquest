package com.javaquest.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents one concept room in the game map.
 * Each room must contain exactly 5 questions:
 * - 3 MCQ
 * - 2 CODE
 */
public class Room {
    private final int roomId;
    private final String conceptName;
    private final String difficulty;
    private final String conceptSummary;
    private final List<Question> questions;

    public Room(int roomId, String conceptName, String difficulty, String conceptSummary, List<Question> questions) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("roomId must be positive");
        }
        this.roomId = roomId;
        this.conceptName = requireNotBlank(conceptName, "conceptName");
        this.difficulty = requireNotBlank(difficulty, "difficulty");
        this.conceptSummary = requireNotBlank(conceptSummary, "conceptSummary");
        Objects.requireNonNull(questions, "questions cannot be null");
        if (questions.size() != 5) {
            throw new IllegalArgumentException("Each room must contain exactly 5 questions");
        }

        long mcqCount = questions.stream().filter(q -> "MCQ".equals(q.getType())).count();
        long codeCount = questions.stream().filter(q -> "CODE".equals(q.getType())).count();
        if (mcqCount != 3 || codeCount != 2) {
            throw new IllegalArgumentException("Each room must contain 3 MCQ and 2 CODE questions");
        }
        this.questions = List.copyOf(questions);
    }

    public int getRoomId() {
        return roomId;
    }

    public String getConceptName() {
        return conceptName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getConceptSummary() {
        return conceptSummary;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    private static String requireNotBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value;
    }
}
