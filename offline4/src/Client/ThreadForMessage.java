package Client;

import util.SocketWrapper;

import java.io.IOException;

public class ThreadForMessage implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;

    public ThreadForMessage(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                String s = (String)socketWrapper.read();
                System.out.println(s);
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



