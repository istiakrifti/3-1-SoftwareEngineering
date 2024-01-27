package Server;

import Client.User;
import util.SocketWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ThreadForAdministrator implements Runnable {

    private Thread thr;
    private SocketWrapper socketWrapper;
    HashMap<String,SocketWrapper> clientList;
    Administrator admin;
    String name;

    public ThreadForAdministrator(Administrator admin) {
//        this.socketWrapper = socketWrapper;
        this.admin = admin;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                String[] str = s.split(" ");

                if(str[0].equalsIgnoreCase("I")) {
                    admin.increasePrice(str[1],Double.parseDouble(str[2]));
                }
                else if(str[0].equalsIgnoreCase("D")) {
                    admin.decreasePrice(str[1],Double.parseDouble(str[2]));
                }
                else if(str[0].equalsIgnoreCase("C")) {
                    admin.changeInStockCount(str[1],Integer.parseInt(str[2]));
                }
//                System.out.println("dfasddf");
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



