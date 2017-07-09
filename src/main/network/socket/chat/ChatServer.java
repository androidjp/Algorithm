package main.basic.socket.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 聊天系统---服务端线程
 * Created by androidjp on 2017/1/19.
 */
public class ChatServer extends Thread {
    ///ServerSocket
    private ServerSocket serverSocket = null;
    ///默认服务端传输使用端口
    private static final int PORT = 9999;
    ///写
    private BufferedWriter writer;
    ///读
    private BufferedReader reader;

    public  ChatServer() {
        try {
            CreateSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("创建ServerSorket异常");
        }
    }

    @Override
    public void run() {
        Socket client;
        String txt;
        try{
            ///始终监听
            while(true){
                client  = responseSocket();
                while(true){
                    ///接收客户端消息
                    txt = receiveMsg(client);
                    System.out.println(txt);
                    sendMsg(client,txt);
                    ///中断，继续等待连接请求
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("响应并获取客户端socket对象异常！");
        }
    }

    private void CreateSocket() throws IOException {
        serverSocket = new ServerSocket(PORT, 100);
    }

    ///响应Socket的请求
    private Socket responseSocket() throws IOException {
        return serverSocket.accept();
    }

    ////关闭socket
    private void closeSocket(Socket socket) throws IOException {
        if (reader != null)
            reader.close();
        if (writer != null)
            writer.close();
        socket.close();
    }

    ////发送消息
    private void sendMsg(Socket socket, String msg) throws IOException {
        writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        writer.write(msg + "\n");
        writer.flush();
    }
    //接收消息
    private String receiveMsg(Socket socket) throws IOException {
        reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        String txt = reader.readLine();
        return txt;
    }

}
