package org.example;
import java.util.ArrayList;
import java.util.List;

public class CustomMealRequest {
    private int requestId;
    private int customerId;
    private List<String> ingredients;
    private String status;
    public CustomMealRequest(int requestId, int customerId, List<String> ingredients) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.ingredients = new ArrayList<>(ingredients);
        this.status = "Pending";
    }
    public int getCustomerId() {
        return customerId;
    }
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }
}
