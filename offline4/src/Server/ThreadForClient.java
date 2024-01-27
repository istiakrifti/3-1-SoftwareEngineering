package Server;

import Client.User;
import util.SocketWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ThreadForClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    HashMap<String,SocketWrapper> clientList;
    HashMap<String,User> userList;
    Administrator admin;
    String name;
    public ThreadForClient(SocketWrapper socketWrapper,HashMap<String,SocketWrapper> clientList,HashMap<String,User> userList,Administrator admin,String name) {
        this.socketWrapper = socketWrapper;
        this.clientList = clientList;
        this.userList = userList;
        this.admin = admin;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            User user;
            if(!clientList.containsKey(name)) {
                user = new User(name);
//                Server.login.add(name);
                clientList.put(name,socketWrapper);
                userList.put(name,user);
            }
            else {
                clientList.put(name,socketWrapper);
                user = userList.get(name);
                if(user.getMsg().size()!=0) {
                    for(String s: user.getMsg()) {
                        socketWrapper.write(s);
                    }
                    user.getMsg().clear();
//                    user.setNotified(false);
                }

            }
            while (true) {
                Object ob = socketWrapper.read();
                String command = (String)ob;
                String[] str = command.split(" ");
                String list="";
                if(str[0].equalsIgnoreCase("V")) {
                    for(Stock st : admin.getStockList()) {
                        if(st.view(name)) list+=st.getName()+"\n";
                    }
                    socketWrapper.write(list);
                }
                else {
                    Stock stock=null;
                    for(int i=0;i<admin.getStockList().size();i++) {
                        if(admin.getStockList().get(i).getName().equalsIgnoreCase(str[1])) {
                            stock = admin.getStockList().get(i);
                            break;
                        }
                    }
                    if(stock==null) socketWrapper.write(str[1]+" Doesn't exist");
                    else if(str[0].equalsIgnoreCase("S")) {
                        if(stock.subscribe(user)) {
                            socketWrapper.write("Successfully Subscribed");
                        }
                        else {
                            socketWrapper.write("Already subscribed");
                        }
                    }
                    else if(str[0].equalsIgnoreCase("U")) {
                        if(stock.unsubscribe(user)) {
                            socketWrapper.write("Successfully unsubscribed");
                        }
                        else {
                            socketWrapper.write("Did not subscribe");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
                Server.login.remove(name);
//                socketWrapper=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



