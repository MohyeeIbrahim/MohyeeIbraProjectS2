package org.example;
import java.util.ArrayList;
import java.util.List;

public class CustomMealRequest {
    private int requestId;
    private int customerId;
    private List<String> ingredients;

    public CustomMealRequest(int requestId, int customerId, List<String> ingredients) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.ingredients = new ArrayList<>(ingredients);
        String status = "Pending";
    }
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setIngredients(List<String> updatedIngredients) {
        this.ingredients = updatedIngredients;
    }
}
