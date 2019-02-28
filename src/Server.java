import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

class Server {
    private ServerSocket serverSocket;
    Socket[] clientSockets;
    private String ipAddress;
    private Lobby lobby;
    private DatagramSocket broadcaster;

    Server() throws IOException {
        this.serverSocket = new ServerSocket(4444);
        this.ipAddress = Network.findIPaddress();
        this.lobby = new Lobby();
        new connectClients().start();
    }

    // finds the ip address of current device

    String getIpAddress() {
        return ipAddress;
    }
    
    public class connectClients extends Thread {
        public void run() {
            try {
                broadcaster = new DatagramSocket(4445);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while(lobby.getState()){
                Socket clientSocket = null;
                new broadcastIP().start();

                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                new Thread(new connection(clientSocket)).start();
            }
        }
    }
    
    public class broadcastIP extends Thread {
        public void run() {
            while (lobby.getState()) {
                byte[] buf = new byte[1];

                InetAddress group = null;
                try {
                    group = InetAddress.getByName("203.0.113.0");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                DatagramPacket packet;
                packet = new DatagramPacket(buf, buf.length, group, 4446);
                try {
                    broadcaster.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            broadcaster.close();
        }
    }


    public class connection implements Runnable {
        Socket clientSocket;

        connection(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inLine;
                while ((inLine = in.readLine()) != null) {
                    System.out.println(inLine);
                    readClientMessage(inLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void readClientMessage(String inputString) {
        ListIterator<String> in;
        List<String> inputList = Arrays.asList(inputString.split(","));
        in = inputList.listIterator();

        while (in.hasNext()) {
            switch (in.next()) {
                case "NAME":
                    lobby.addPlayer(new Player(in.next()));
                    break;
            }
        }
    }
}
