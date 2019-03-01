package numbersgame;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

class Server {
    ServerSocket ss;
    private Lobby lobby;
    private String ipAddress;
    private DatagramSocket broadcaster;
    private String hostName;


    Server(Player host, Game game) throws IOException {
        this.ss = new ServerSocket(3333);
        this.ipAddress = Network.findIPaddress();
        this.lobby = new Lobby();
        this.hostName = host.getPlayerName();

        new broadcastIP().start();
        startServer();
    }

    String getIpAddress() {
        return ipAddress;
    }

    // broadcasts IP of server by broadcasting datagram packet
    // address 224.0.0.0, port 4446
    private class broadcastIP extends Thread {
        public void run() {
            while (lobby.getState()) {
                byte[] buf;

                InetAddress group = null;
                try {
                    group = InetAddress.getByName("224.0.0.0");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                DatagramPacket packet;

                buf = hostName.getBytes();
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

    // start server, connect clients to client handler
    private void startServer() throws IOException {
        while (true) {
            Socket s = null;

            try {
                s = ss.accept();
                System.out.println("A new client has connected : " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("create new thread for client");

                // create new thread
                Thread t = new ClientHandler(s, dis, dos);

                t.start();

            } catch (Exception e) {
                assert s != null;
                s.close();
                e.printStackTrace();
            }

        }
    }

    class ClientHandler extends Thread {
        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;

        ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }

        @Override
        public void run() {
            String recieved;
            String toreturn;
            while (true) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                    System.out.println("waiting for client input");
                    while ((recieved = in.readLine()) != null) {
                        System.out.println(recieved);
                        readClientMessage(recieved);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

                    break;
            }
        }
    }
}
