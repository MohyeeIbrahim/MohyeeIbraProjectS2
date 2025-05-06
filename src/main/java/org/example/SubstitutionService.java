package org.example;

import java.util.*;

public class SubstitutionService {
    private final Map<String, String> substitutionMap;

    public SubstitutionService() {
        substitutionMap = new HashMap<String,String>();
        substitutionMap.put("butter", "olive oil");
        substitutionMap.put("sugar", "honey");
    }
    public List<String> applySubstitution(CustomMealRequest request, ChefManager chefManager, int chefId) {
        List<String> updatedIngredients = new ArrayList<>();
        for (String ingredient : request.getIngredients()) {
            if (substitutionMap.containsKey(ingredient)) {
                String substitute = substitutionMap.get(ingredient);
                updatedIngredients.add(substitute);
                chefManager.sendAlertToChef(chefId,
                        "Ingredient substitution applied: '" + ingredient + "' replaced with '" + substitute + "'");            } else {
                updatedIngredients.add(ingredient);
            }
        }
        request.setIngredients(updatedIngredients);
        return updatedIngredients;
    }
}
