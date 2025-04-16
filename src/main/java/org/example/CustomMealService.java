package org.example;

import java.util.ArrayList;
import java.util.List;

public class CustomMealService {
    private IngredientValidator validator=new IngredientValidator();
    private List<String> ingredients=new ArrayList<String>();
    private List<CustomMealRequest> customMealRequests = new ArrayList<>();
    private int nextRequestId = 1;

    public String createCustomMealRequest(int customerId, List<String> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Please select at least one ingredient");
        }
        CustomMealRequest request = new CustomMealRequest(nextRequestId++, customerId, ingredients);
        customMealRequests.add(request);
        return "Your custom meal request has been submitted";
    }
    public boolean validateIngredients(List<String> selectedIngredients) {
        return validator.isValidCombination(selectedIngredients);
    }
}
