import java.util.Scanner;
import builders.ShakeBuilder;
import director.ShakeOrderDirector;

public class ShakeShopManagement {
    public static boolean orderStatus = false,shakeCount=false;
    public static String lactose,candy,cookies;
    public static void askForCustomIngredients()
    {
        System.out.print("Lactose-free or not:(y/n)");
        Scanner scanner2 = new Scanner(System.in);
        lactose = scanner2.nextLine();
        System.out.print("Candy or not:(y/n)");
        Scanner scanner3 = new Scanner(System.in);
        candy = scanner3.nextLine();
        System.out.print("Cookies or not:(y/n)");
        Scanner scanner4 = new Scanner(System.in);
        cookies = scanner4.nextLine();

    }
    public static void main(String[] args) {

        ShakeOrderDirector shakeOrderDirector = new ShakeOrderDirector();
        while (true)
        {
            System.out.println("Press o to order a Shake");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(!input.equalsIgnoreCase("o"))
            {
                System.out.println("Invalid input");
            }
            else
            {
                if(orderStatus==false)
                {
                    shakeOrderDirector.openOrder();
                    orderStatus=true;
                }

                while(true)
                {
                    if(shakeCount==false)
                    {
                        System.out.println("Which type of shake do you prefer?\n(Press Ch for ChocolateShake, Co for CoffeeShake, V for VanillaShake, " +
                                "S for StrawberryShake, Z for ZeroShake, e for close order)");
                    }
                    else
                    {
                        System.out.println("Do you want more shakes?\nPress Ch for ChocolateShake, Co for CoffeeShake, V for VanillaShake, " +
                                "S for StrawberryShake, Z for ZeroShake, e for close order)");
                    }

                    Scanner scanner1 = new Scanner(System.in);
                    String input1 = scanner1.nextLine().toLowerCase();
//                    if(!input1.equalsIgnoreCase("e") || !input1.equalsIgnoreCase("o")) askForCustomIngredients();
                    ShakeBuilder shakeBuilder = new ShakeBuilder();
                    switch (input1)
                    {
                        case "ch":
                            askForCustomIngredients();
                            shakeOrderDirector.MakeChocolateShake(shakeBuilder,lactose,candy,cookies);
                            shakeCount=true;
                            break;
                        case "co":
                            askForCustomIngredients();
                            shakeOrderDirector.MakeCoffeeShake(shakeBuilder,lactose,candy,cookies);
                            shakeCount=true;
                            break;
                        case "c":
                            askForCustomIngredients();
                            shakeOrderDirector.MakeStrawberryShake(shakeBuilder,lactose,candy,cookies);
                            shakeCount=true;
                            break;
                        case "v":
                            askForCustomIngredients();
                            shakeOrderDirector.MakeVanillaShake(shakeBuilder,lactose,candy,cookies);
                            shakeCount=true;
                            break;
                        case "z":
                            askForCustomIngredients();
                            shakeOrderDirector.MakeZeroShake(shakeBuilder,lactose,candy,cookies);
                            shakeCount=true;
                            break;
                        case "e":
                            if(shakeCount==false)
                            {
                                System.out.println("You have to order at least one shake");
                            }
                            else
                            {
                                shakeOrderDirector.closeOrder();
                                orderStatus = false;
                            }
                            break;
                        case "o":
                            System.out.println("One order is ongoing.Let it complete first");
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }

                    if(orderStatus==false)
                    {
                        break;
                    }
                }
            }
            shakeCount=false;
        }


    }
}
