package Server;

import Client.User;
import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private int count;
    private double price;
    private List<User> subscribers;

    public Stock(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
        subscribers = new ArrayList<>();
    }

    public boolean subscribe(User user) {
        for(int i=0;i<subscribers.size();i++) {
            if(subscribers.get(i).equals(user)) {
//                System.out.println("Already subscribed");
                return false;
            }
        }
        subscribers.add(user);
        return true;
    }

    public boolean unsubscribe(User user) {
        for(int i=0;i<subscribers.size();i++) {
            if(subscribers.get(i).equals(user)) {
                subscribers.remove(user);
                return true;
            }
        }
//        System.out.println("Not subscribed yet");
        return false;
    }

    public boolean view(String name) {
        for(User u : subscribers) {
            if(u.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public void notifyUsers(String msg) throws Exception {
        if(Server.socketWrapper==null) {
            System.out.println("bnbn");
//            notification.add(e);
            for(User u: subscribers) {
                u.update(msg);
            }
        }
        else
        {
//            notification.add(e);
            for(User u: subscribers) {
//                for(String s : Server.login) {
//                    if(!s.equalsIgnoreCase(u.getName())) {
                u.setNotified(false);
            }
            for(String s : Server.login) {
                if(this.isSubscribed(s)) {
                    Server.userList.get(s).setNotified(true);
                    Server.clientList.get(s).write(msg);
                }
            }
            for(User u: subscribers) {
//                for(String s : Server.login) {
//                    if(!s.equalsIgnoreCase(u.getName())) {
                       if(!u.isNotified()) u.update(msg);
//                    }
//                }
            }

//                    if(st.isSubscribed(Server.name)) Server.socketWrapper.write(msg);
        }
    }

    public boolean isSubscribed(String name) {
        for(User u: subscribers) {
            if(u.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }
}
