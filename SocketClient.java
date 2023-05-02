import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.net.*;
import java.util.*;

public class SocketClient {
    private String address = "127.0.0.1";// 連線的ip
    private int port = 8765;// 連線的port

    public SocketClient(String target_ip) {
        this.address = target_ip;
    }

    public void return_patch(String err) {
        String ip_addr = new String();
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while( networkInterfaceEnumeration.hasMoreElements()){
                for ( InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses())
                    if ( interfaceAddress.getAddress().isSiteLocalAddress()) {
                        // System.out.println(interfaceAddress.getAddress().getHostAddress());
                        ip_addr = "" + interfaceAddress.getAddress().getHostAddress();
                    }
                        
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            BufferedOutputStream out = new BufferedOutputStream(client
                    .getOutputStream());
            // 送出字串
            // out.write(ip_addr.getBytes());
            out.write(err.getBytes());
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
        SocketClient socketclient = new SocketClient(args[0]);
        socketclient.return_patch(args[1]);
    }
}