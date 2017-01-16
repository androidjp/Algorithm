package main.basic.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by androidjp on 2016/11/15.
 */
public class Client {
    public String id = "客户端";
    public int number;

    public Client() {
    }

    public Client(int number) {
    }

    public void TCPConnection(String text){
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            InputStreamReader reader  = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer_reader = new BufferedReader(reader);
            PrintWriter writer  = new PrintWriter(socket.getOutputStream());
            String content = text;
            if (content==null || content.length()==0){
                content= "空消息";
            }
            writer.println(content);
            String response = buffer_reader.readLine();
            System.out.println("服务端说："+ response);
            writer.close();
            buffer_reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void UDPConnection(){
        try{
            DatagramSocket sendSocket = new DatagramSocket();
            String msg = "aaa";
            byte[] buf = msg.getBytes();
            int port = 8888;
            InetAddress ip = InetAddress.getLocalHost();
            DatagramPacket sendPacket = new DatagramPacket(buf,buf.length,ip,port);
            sendSocket.send(sendPacket);
            byte[] getBuf= new byte[1024];
            DatagramPacket getPacket = new DatagramPacket(getBuf,getBuf.length);
            sendSocket.receive(getPacket);
            String backMsg =  new String(getBuf,0,getPacket.getLength());
            System.out.println("返回结果是："+backMsg);

            sendSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
