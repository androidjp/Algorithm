package main.basic.network;

import java.io.IOException;
import java.net.*;

/**
 * Created by androidjp on 2016/11/15.
 */
public class Server {
    ///TCP 多对一 连接
    private ServerSocket serverSocket;
    private int port = 8888;
    public Server() {
        launchTCP();
    }

    public Server(int port){
        this.port = port;
        createServerSocket();
        launchTCP();
    }

    public void createServerSocket(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 多对一的TCP连接启动过程
     */
    public void launchTCP(){
        if (this.serverSocket==null)
            createServerSocket();
        while(true){
            try {
                Socket socket = this.serverSocket.accept();
                SocketHandler handler = new SocketHandler(socket);
                new Thread(handler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void launchUDP(){
        try{
            InetAddress ip  =InetAddress.getLocalHost();
            int port = 8888;
            DatagramSocket getSocket = new DatagramSocket(port,ip);
//            getSocket.connect(ip,888);///设置限制请求方的地址和端口
            byte[] buf = new byte[1024];
            DatagramPacket getPacket = new DatagramPacket(buf,buf.length);
            getSocket.receive(getPacket);
            String getMsg = new String(buf,0,getPacket.getLength());
            //输出
            InetAddress sendIP = getPacket.getAddress();
            int sendPort = getPacket.getPort();
            SocketAddress sendAddress = getPacket.getSocketAddress();
            String feedback="我收到了";
            byte[] backBuf = feedback.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(backBuf,backBuf.length,sendAddress);
            getSocket.send(sendPacket);
            getSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }
}
