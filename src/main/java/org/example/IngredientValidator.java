package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientValidator {

    private final List<List<String>> validCombinations;
    private List<String> validCombos = new ArrayList<>();
    public IngredientValidator() {
        this.validCombinations = Arrays.asList(
                Arrays.asList("tomato", "basil", "mozzarella"),
                Arrays.asList("chicken", "broccoli", "rice"),
                Arrays.asList("beef", "lettuce", "tomato")
        );
        validCombos = new ArrayList<>();
    }
    public IngredientValidator(List<List<String>> customCombinations) {
        this.validCombinations = new ArrayList<>(customCombinations);
    }


    public boolean isValidCombination(List<String> ingredients) {
        for (List<String> validCombo : validCombinations) {
            if (validCombo.size() == ingredients.size() &&
                    validCombo.containsAll(ingredients)) {
                return true;
            }
        }
        return false;
    }

}
