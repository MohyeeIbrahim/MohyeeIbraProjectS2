package org.example;

import java.util.ArrayList;
import java.util.List;

public class IngredientSuggestionService {
    private List<String> availableIngredients = List.of("Chicken","Rice", "mushroom", "tomato","almond milk","soy milk","oat milk");

    public boolean isAvailable(String ingredient) {
        return availableIngredients.contains(ingredient);
    }

    public List<String> suggestAlternatives(String unavailable, Customer customer) {
        List<String> suggestions = new ArrayList<>();

        if (unavailable.equalsIgnoreCase("milk")) {
            suggestions.add("almond milk");
            suggestions.add("soy milk");
            suggestions.add("oat milk");
        }
        if (suggestions.isEmpty()) {
            return List.of("No suitable alternative is available.");
        }

        return suggestions;
    }
}
