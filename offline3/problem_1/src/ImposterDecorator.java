public class ImposterDecorator implements CrewmateTask{
    Crewmate crewmate;

    public ImposterDecorator(Crewmate crewmate) {
        this.crewmate = crewmate;
    }


    @Override
    public void login() {
        crewmate.login();
        System.out.println("We won’t tell anyone; you are an imposter.\n");
    }

    @Override
    public void repair() {
        crewmate.repair();
        System.out.println("Damaging the spaceship.\n");
    }

    @Override
    public void work() {
        crewmate.work();
        System.out.println("Trying to kill a crewmate.\n" +
                "Successfully killed a crewmate.\n");
    }

    @Override
    public void logout() {
        crewmate.logout();
        System.out.println("See you again Comrade Imposter.\n");
    }
}
