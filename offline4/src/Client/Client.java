package Client;

import util.SocketWrapper;

import java.util.Scanner;

public class Client {
    private String name;

    public Client(String serverAddress, int serverPort) {
        try {
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            System.out.println("Enter your name: ");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();

            socketWrapper.write(name);

            new ReadThreadClient(socketWrapper);
            new ThreadForMessage(socketWrapper);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        new Client(serverAddress, serverPort);
    }
}

