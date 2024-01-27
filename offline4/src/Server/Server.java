package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Client.User;
import util.SocketWrapper;

public class Server {
    private ServerSocket serverSocket;
    private final String INPUT_FILE_NAME="D:/3-1/308/offline4/src/Server/init_stocks.txt";
    public static HashMap<String,SocketWrapper> clientList = new HashMap<>();
    private String name;
    public static List<String> login = new ArrayList<>();
    public static SocketWrapper socketWrapper=null;
    public static HashMap<String, User> userList=new HashMap<>();
    Server(Administrator admin) throws Exception {
        readFile(admin);
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                new ThreadForAdministrator(admin);
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket,admin);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket,Administrator admin) throws Exception {

        socketWrapper = new SocketWrapper(clientSocket);

        String name = (String)socketWrapper.read();
        System.out.println("Received: " + name);
        login.add(name);
        String st = "";
        for(Stock s : admin.getStockList()) {
            st += s.getName()+" "+s.getCount()+" "+s.getPrice()+"\n";
        }
        socketWrapper.write(st);
//        socketWrapper.write(admin.getNotification().get(0).getValue());
//        System.out.println(admin.getNotification().get(0).getValue());
        new ThreadForClient(socketWrapper,clientList,userList,admin,name);

    }

    public void readFile(Administrator admin) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] obj=line.split(" ");
            Stock stock=new Stock(obj[0],Integer.parseInt(obj[1]),Double.parseDouble(obj[2]));
            admin.getStockList().add(stock);
        }

        br.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String args[]) throws Exception {
        Administrator administrator = new Administrator();
        new Server(administrator);
    }
}
