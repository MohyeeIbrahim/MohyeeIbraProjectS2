package org.example;

import java.util.*;

public class SubstitutionService {
    private final Map<String, String> substitutionMap;

    public SubstitutionService() {
        substitutionMap = new HashMap<String,String>();
        substitutionMap.put("butter", "olive oil");
        substitutionMap.put("sugar", "honey");
    }
    public List<String> applySubstitution(CustomMealRequest request, AlertService alertService) {
        List<String> updatedIngredients = new ArrayList<>();
        for (String ingredient : request.getIngredients()) {
            if (substitutionMap.containsKey(ingredient)) {
                String substitute = substitutionMap.get(ingredient);
                updatedIngredients.add(substitute);
                alertService.sendAlert("Ingredient substitution applied: '" + ingredient + "' replaced with '" + substitute + "'");
            } else {
                updatedIngredients.add(ingredient);
            }
        }
        return updatedIngredients;
    }
}
