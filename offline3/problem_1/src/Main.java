import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CrewmateTask crewmate=null;
        boolean flag = false;
        while(true) {
            String input = scanner.nextLine();
            String[] str = input.split(" ");

            if(str[0].equalsIgnoreCase("login")) {
                if(!flag) {
                    if (str[1].substring(0, 4).equalsIgnoreCase("crew")) {
                        crewmate = new Crewmate(str[1]);
                        crewmate.login();
                    } else if (str[1].substring(0, 3).equalsIgnoreCase("imp")) {
                        crewmate = new ImposterDecorator(new Crewmate(str[1]));
                        crewmate.login();
                    }
                    flag = true;
                }
                else System.out.println("One crewmate is logged in.Try again later");
            }
            else if(str[0].equalsIgnoreCase("repair")) {
                if(flag) crewmate.repair();
                else System.out.println("login first");
            }
            else if(str[0].equalsIgnoreCase("work")) {
                if(flag) crewmate.work();
                else System.out.println("login first");
            }
            else if(str[0].equalsIgnoreCase("logout")) {
                if(flag)
                {
                    crewmate.logout();
                    flag = false;
                }
                else System.out.println("login first");
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }

}
