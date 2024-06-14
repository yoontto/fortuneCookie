package com.fortunecookie.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GeminiResponse {

    private List<Candidate> candidates;

    @Getter
    public static class Candidate {
        private Content contents;
        private String finishReason;
        private int index;
        List<SafetyRating> safetyRatings;
    }

    @Getter
    public static class Content {
        private List<Part> parts;
        private String role;
    }

    @Getter
    public static class Part {
        private String text;
    }

    @Getter
    public static class SafetyRating {
        private String category;
        private String probability;
    }

}
