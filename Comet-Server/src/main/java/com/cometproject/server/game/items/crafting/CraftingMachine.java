package com.cometproject.server.game.items.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CraftingMachine {
    private final int baseId;
    private final Map<Integer, String> allowedItems;
    private final List<CraftingRecipe> publicRecipes;
    private final List<CraftingRecipe> secretRecipes;

    public CraftingMachine(int baseId) {
        this.baseId = baseId;
        this.allowedItems = new HashMap<>();
        this.publicRecipes = new ArrayList<>();
        this.secretRecipes = new ArrayList<>();
    }

    public int getBaseId() { return this.baseId; }

    public Map<Integer, String> getAllowedItems() { return this.allowedItems; }

    public List<CraftingRecipe> getPublicRecipes() { return this.publicRecipes; }

    public List<CraftingRecipe> getSecretRecipes() { return this.secretRecipes; }

    public CraftingRecipe getRecipeByProductData(String productdata) {
        CraftingRecipe result = null;

        for(CraftingRecipe recipe : this.publicRecipes) {
            if(recipe.getResultProductData().equals(productdata))
                result = recipe;
        }

        return result;
    }
}
