public class Command {
    String command;
    private FileSystemComponent currentComponent;

    public Command(FileSystemComponent currentComponent) {
        this.currentComponent = currentComponent;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void executeCommand() {
        String[] str = command.split(" ");

        if(str[0].equals("cd")) {
            if(str.length==2) {
                if(str[1].equals("~")) {
                    currentComponent = Root.getRoot();
                }
                else
                {
                    if(currentComponent.changeDirectory(str[1])!=null) {
                        currentComponent = currentComponent.changeDirectory(str[1]);
                    }
                }
            }
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("mkdrive")) {
            if(str.length==2) currentComponent.mkdrive(str[1],currentComponent);
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("mkdir")) {
            if(str.length==2) currentComponent.mkdir(str[1],currentComponent);
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("touch")) {
            if(str.length==3) currentComponent.touch(str[1],Double.parseDouble(str[2]),currentComponent);
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("ls")) {
            if(str.length==2) currentComponent.ls(str[1]);
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("list")) {
            if(str.length==1) currentComponent.list();
            else System.out.println("Invalid parameter");
        }
        else if(str[0].equals("delete")) {
            if(str.length==3) {
                if(str[1].equals("-r")) {
                    currentComponent.deleteRecursively(str[2]);
                }
                else System.out.println("Invalid parameter");
            }
            else if(str.length==2) {
                currentComponent.Delete(str[1]);
            }
            else System.out.println("Invalid parameter");
        }
        else System.out.println("Invalid input");
    }
}
