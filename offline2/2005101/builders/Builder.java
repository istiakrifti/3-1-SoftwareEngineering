package builders;

import Ingredients.AdditionalIngredients;
import Ingredients.BaseIngredient;
import Ingredients.CustomIngredients;
import shakes.Shake;
import shakes.ShakeType;

public interface Builder {
    void setType(ShakeType type);
    void addBasicIngredients(BaseIngredient baseIngredient);
    void setBasePrice(double price);
    void addAdditionalIngredients(AdditionalIngredients additionalIngredients);
    void addCustomIngredients(CustomIngredients customIngredients);
    void calculatePrice(String lactose,String candy,String cookies);
    Shake getShake();
}

