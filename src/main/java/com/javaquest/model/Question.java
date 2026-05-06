package com.javaquest.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable question model used by the game engine.
 * Supports two types of questions:
 * - MCQ: multiple-choice with exactly 4 options
 * - CODE: short code/concept answer with no options
 */
public final class Question {
    private static final String TYPE_MCQ = "MCQ";
    private static final String TYPE_CODE = "CODE";

    private final String id;
    private final String text;
    private final String[] options;
    private final String correctAnswer;
    private final String explanation;
    private final String type;

    public Question(String id, String text, String[] options, String correctAnswer, String explanation, String type) {
        this.id = requireNotBlank(id, "id");
        this.text = requireNotBlank(text, "text");
        this.correctAnswer = requireNotBlank(correctAnswer, "correctAnswer");
        this.explanation = requireNotBlank(explanation, "explanation");
        this.type = requireNotBlank(type, "type");

        if (!TYPE_MCQ.equals(this.type) && !TYPE_CODE.equals(this.type)) {
            throw new IllegalArgumentException("Question type must be MCQ or CODE");
        }

        if (TYPE_CODE.equals(this.type)) {
            if (options != null) {
                throw new IllegalArgumentException("CODE questions must have options = null");
            }
            this.options = null;
        } else {
            if (options == null || options.length != 4) {
                throw new IllegalArgumentException("MCQ questions must have exactly 4 options");
            }
            for (String option : options) {
                requireNotBlank(option, "option");
            }
            this.options = Arrays.copyOf(options, options.length);
        }
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options == null ? null : Arrays.copyOf(options, options.length);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getType() {
        return type;
    }

    private static String requireNotBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value;
    }
}
