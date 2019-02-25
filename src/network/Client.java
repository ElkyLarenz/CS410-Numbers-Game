package network;

import java.net.Socket;

public class Client {
    Boolean isHost;
    Socket socket;
    Server server;

    public Client() {
        this.isHost = false;
        this.socket = new Socket();
    }

    public void becomeHost(){
        this.isHost = true;
    }

    public boolean connectServer(){
        return true;
    }
}
