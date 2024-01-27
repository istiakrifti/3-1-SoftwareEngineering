package Client;

import util.SocketWrapper;

import java.io.IOException;
import java.util.Scanner;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private String name;
    private User user;
    boolean flag=false;
    public ReadThreadClient(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
//            System.out.println("Command");

            while (true) {
//                String s = (String)socketWrapper.read();
//                System.out.println(s);
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                socketWrapper.write(command);
//                UserMessage msg = new UserMessage(user, command);
//                Object obj = socketWrapper.read();
//                if(obj!=null) {
//                    String str = (String)obj;
//                    System.out.println(str);
//                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



