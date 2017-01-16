package main.basic.network;

import java.io.*;
import java.net.Socket;

/**
 * Created by androidjp on 2016/11/15.
 */
public class SocketHandler implements Runnable{
    private Socket socket;
    public SocketHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer_reader = new BufferedReader(reader);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            String client = "["+socket.getInetAddress().toString()+socket.getPort()+"] ";
            String request = buffer_reader.readLine();
            System.out.println("客户端"+client+"说："+request);
            writer.println("哈哈，我收到了亲~");
            writer.flush();
            writer.close();
            buffer_reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
