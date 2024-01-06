package director;

import builders.Builder;
import Ingredients.AdditionalIngredients;
import Ingredients.BaseIngredient;
import Ingredients.CustomIngredients;
import shakes.Shake;
import shakes.ShakeType;

public class ShakeOrderDirector {

    private Builder shakeBuilder;
    private Order order;
    private int id=0;

    public void openOrder() {
        order = new Order(++id);

    }

    public void closeOrder() {
        System.out.println("Order is closed.");
        System.out.println("Order id: #"+order.getId());
        System.out.println("Your ordered Shake items: ");
        for(int i=0;i<order.shakeList.size();i++)
        {
            order.shakeList.get(i).Print();
        }
    }

    public void MakeChocolateShake(Builder shakeBuilder, String lactose, String candy, String cookies)
    {
        this.shakeBuilder=shakeBuilder;
        shakeBuilder.setType(ShakeType.ChocolateShake);
        shakeBuilder.setBasePrice(230);
        if(lactose.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Almond_milk);
        else shakeBuilder.addBasicIngredients(BaseIngredient.Milk);
        shakeBuilder.addBasicIngredients(BaseIngredient.Sugar);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Chocolate_syrup);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Chocolate_ice_cream);
        if(candy.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Candy);
        if(cookies.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Cookies);
        shakeBuilder.calculatePrice(lactose,candy,cookies);
        Shake shake = shakeBuilder.getShake();
        order.shakeList.add(shake);
        System.out.println(order.shakeList.size()+" shake(s) added to your order");
    }

    public void MakeCoffeeShake(Builder shakeBuilder, String lactose, String candy, String cookies)
    {
        this.shakeBuilder=shakeBuilder;
        shakeBuilder.setType(ShakeType.CoffeeShake);
        shakeBuilder.setBasePrice(250);
        shakeBuilder.addBasicIngredients(BaseIngredient.Milk);
        shakeBuilder.addBasicIngredients(BaseIngredient.Sugar);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Coffee);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Jello);
        if(lactose.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Almond_milk);
        if(candy.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Candy);
        if(cookies.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Cookies);
        shakeBuilder.calculatePrice(lactose,candy,cookies);
        Shake shake = shakeBuilder.getShake();
        order.shakeList.add(shake);
        System.out.println(order.shakeList.size()+" shake(s) added to your order");
    }

    public void MakeStrawberryShake(Builder shakeBuilder, String lactose, String candy, String cookies)
    {
        this.shakeBuilder=shakeBuilder;
        shakeBuilder.setType(ShakeType.StrawberryShake);
        shakeBuilder.setBasePrice(200);
        if(lactose.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Almond_milk);
        else shakeBuilder.addBasicIngredients(BaseIngredient.Milk);
        shakeBuilder.addBasicIngredients(BaseIngredient.Sugar);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Strawberry_syrup);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Strawberry_ice_cream);
        if(candy.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Candy);
        if(cookies.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Cookies);
        shakeBuilder.calculatePrice(lactose,candy,cookies);
        Shake shake = shakeBuilder.getShake();
        order.shakeList.add(shake);
        System.out.println(order.shakeList.size()+" shake(s) added to your order");
    }

    public void MakeVanillaShake(Builder shakeBuilder, String lactose, String candy, String cookies)
    {
        this.shakeBuilder=shakeBuilder;
        shakeBuilder.setType(ShakeType.VanillaShake);
        shakeBuilder.setBasePrice(190);
        if(lactose.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Almond_milk);
        else shakeBuilder.addBasicIngredients(BaseIngredient.Milk);
        shakeBuilder.addBasicIngredients(BaseIngredient.Sugar);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Vanilla_flavoring);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Jello);
        if(candy.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Candy);
        if(cookies.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Cookies);
        shakeBuilder.calculatePrice(lactose,candy,cookies);
        Shake shake = shakeBuilder.getShake();
        order.shakeList.add(shake);
        System.out.println(order.shakeList.size()+" shake(s) added to your order");
    }

    public void MakeZeroShake(Builder shakeBuilder, String lactose, String candy, String cookies)
    {
        this.shakeBuilder=shakeBuilder;
        shakeBuilder.setType(ShakeType.ZeroShake);
        shakeBuilder.setBasePrice(240);
        if(lactose.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Almond_milk);
        else shakeBuilder.addBasicIngredients(BaseIngredient.Milk);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Sweetener);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Vanilla_flavoring);
        shakeBuilder.addAdditionalIngredients(AdditionalIngredients.Sugar_free_jello);
        if(candy.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Candy);
        if(cookies.equalsIgnoreCase("y")) shakeBuilder.addCustomIngredients(CustomIngredients.Cookies);
        shakeBuilder.calculatePrice(lactose,candy,cookies);
        Shake shake = shakeBuilder.getShake();
        order.shakeList.add(shake);
        System.out.println(order.shakeList.size()+" shake(s) added to your order");
    }


}
