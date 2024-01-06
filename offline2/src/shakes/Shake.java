package shakes;

import Ingredients.AdditionalIngredients;
import Ingredients.BaseIngredient;
import Ingredients.CustomIngredients;

import java.util.List;

public class Shake {
    private ShakeType type;
    private List<BaseIngredient> baseIngredients;
    private List<AdditionalIngredients> additionalIngredients;
    private List<CustomIngredients> customIngredients;
    private double basePrice;
    private double totalPrice;
    public Shake(
            ShakeType type,
                 List<BaseIngredient> baseIngredients,List<AdditionalIngredients> additionalIngredients,
                 List<CustomIngredients> customIngredients,double basePrice,double totalPrice)
    {
        this.type=type;
        this.baseIngredients=baseIngredients;
        this.additionalIngredients=additionalIngredients;
        this.customIngredients=customIngredients;
        this.basePrice=basePrice;
        this.totalPrice=totalPrice;
    }

    public void Print()
    {
        System.out.println("Shake Type: "+type);
        if(baseIngredients.size()!=0) System.out.println("Base Ingredients: ");
        else System.out.println("No Base Ingredient");
        for(int i=0;i<baseIngredients.size();i++)
        {
            System.out.println("\t\t "+(i+1)+". "+baseIngredients.get(i));
        }
        System.out.println("Additional Ingredients: ");
        for(int i=0;i<additionalIngredients.size();i++)
        {
            System.out.println("\t\t "+(i+1)+". "+additionalIngredients.get(i));
        }
        if(customIngredients.size()!=0) System.out.println("Custom Ingredients: ");
        else System.out.println("No Custom Ingredient");
        for(int i=0;i<customIngredients.size();i++)
        {
            System.out.println("\t\t "+(i+1)+". "+customIngredients.get(i));
        }
        System.out.println("Base Price: "+basePrice+"tk");
        for(int i=0;i<customIngredients.size();i++)
        {
            if(customIngredients.get(i).equals(CustomIngredients.Almond_milk)) System.out.println("For adding "+customIngredients.get(i)+", Price is increased by 60tk");
            if(customIngredients.get(i).equals(CustomIngredients.Candy)) System.out.println("For adding "+customIngredients.get(i)+", Price is increased by 50tk");
            if(customIngredients.get(i).equals(CustomIngredients.Cookies)) System.out.println("For adding "+customIngredients.get(i)+", Price is increased by 40tk");
        }
        System.out.println("Total Price: "+totalPrice+"tk");
        System.out.println();
    }
}
