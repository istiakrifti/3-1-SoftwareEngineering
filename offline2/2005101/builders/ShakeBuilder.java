package builders;

import Ingredients.AdditionalIngredients;
import Ingredients.BaseIngredient;
import Ingredients.CustomIngredients;
import shakes.Shake;
import shakes.ShakeType;

import java.util.ArrayList;
import java.util.List;

public class ShakeBuilder implements Builder{
    private ShakeType type;
    private List<BaseIngredient> baseIngredients=new ArrayList<>();
    private List<AdditionalIngredients> additionalIngredients=new ArrayList<>();
    private List<CustomIngredients> customIngredients=new ArrayList<>();
    private double basePrice;
    private double totalPrice;

    @Override
    public void setType(ShakeType type) {
        this.type=type;
    }

    @Override
    public void addBasicIngredients(BaseIngredient ingredient) {
        baseIngredients.add(ingredient);
    }

    @Override
    public void setBasePrice(double price) {
        basePrice=price;
    }

    @Override
    public void addAdditionalIngredients(AdditionalIngredients ingredient) {
        additionalIngredients.add(ingredient);
    }

    @Override
    public void addCustomIngredients(CustomIngredients ingredient) {
        customIngredients.add(ingredient);
    }


    @Override
    public void calculatePrice(String lactose,String candy,String cookies) {
        totalPrice = basePrice;
        if(lactose.equalsIgnoreCase("y")) totalPrice+=60;
        if(candy.equalsIgnoreCase("y")) totalPrice+=50;
        if(cookies.equalsIgnoreCase("y")) totalPrice+=40;
    }

    @Override
    public Shake getShake() {
        return new Shake(type,baseIngredients,additionalIngredients,customIngredients,basePrice,totalPrice);
    }

}
