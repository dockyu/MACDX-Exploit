import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.net.*;
import java.util.*;

public class Team1 {
    private String address = "127.0.0.1";// 連線的ip
    private int port = 3001;// 連線的port

    public Team1(String target_ip, int target_port) {
        this.address = target_ip;
        this.port = target_port;
    }

    public void send(String message) {

        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            BufferedOutputStream out = new BufferedOutputStream(client
                    .getOutputStream());
            // 送出字串
            // out.write(ip_addr.getBytes());
            out.write(message.getBytes());
            out.flush();
            out.close();
            out = null;
            client.close();
            client = null;

        } catch (java.io.IOException e) {
            System.out.println("Socket連線有問題 !");
            System.out.println("IOException :" + e.toString());
        }
    }

    public static void main(String args[]) {
        Team1 team1  = new Team1("192.168.42.65", 8765);
        team1.send("Team1");
    }
}