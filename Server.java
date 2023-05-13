import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 初始化
            ServerSocket serverSocket = new ServerSocket(8899);

            // 一旦有人连接立马返回一个 socket 对象
            Socket accept = serverSocket.accept();
            System.out.println("客户端连接成功");
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            System.out.println("serversocket accept:" + line);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}