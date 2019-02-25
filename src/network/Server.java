package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class Server {
    private ServerSocket serverSocket;
    Socket[] clientSockets;
    private String ipAddress;
    private Lobby lobby;

    Server() throws IOException {
        this.serverSocket = new ServerSocket(4444);
        this.ipAddress = findIPaddress();
        this.lobby = new Lobby();
        connectClients();
    }

    // gets the ip address of current device
    private String findIPaddress() throws SocketException, UnknownHostException {
       final DatagramSocket ds = new DatagramSocket();
       ds.connect(InetAddress.getByName("8.8.8.8"), 10002);
       return ds.getLocalAddress().getHostAddress();
    }

    String getIpAddress(){
        return ipAddress;
    }

    private void connectClients() throws IOException {
        while (true) {
            Socket clientSocket;
            clientSocket = serverSocket.accept();

            new Thread(new connection(clientSocket)).start();
        }
    }

    public class connection implements Runnable {
        Socket clientSocket;

        connection(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inLine;
                while((inLine = in.readLine()) != null){
                    System.out.println(inLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
