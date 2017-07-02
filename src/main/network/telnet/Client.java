package main.network.telnet;

import org.apache.commons.net.io.CopyStreamException;
import org.apache.commons.net.io.Util;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

/**
 * Telnet客户端 写法
 * Created by androidjp on 2017/1/19.
 */
public class Client {

    TelnetClient telnet;

    public void start(){
        try{
            telnet = new TelnetClient();
            telnet.connect("remote ip",8899);

            ///操作：获取 telnet.getInputStream() 和 telnet.getOutputStream()

            ///最终断开连接
            telnet.disconnect();

        } catch (SocketException e) {
            e.printStackTrace();
            System.err.println("连接telnet异常");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void readWrite(InputStream remoteInput, OutputStream remoteOutput, InputStream localInput, OutputStream localOutput){
        Thread reader ,writer;

        reader = new Thread(){
            @Override
            public void run() {
                int ch;
                try{
                    ///读本地， 写到远程
                    while (!interrupted()&&(ch=localInput.read())!=-1){
                        remoteOutput.write(ch);
                        remoteOutput.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        writer = new Thread(){
            @Override
            public void run() {
                try{
                    ///将远程输入流数据，复制到本地输出流
                    Util.copyStream(remoteInput,localOutput);
                } catch (CopyStreamException e) {
                    e.printStackTrace();
                }
            }
        };

        ///设置writer线程
        writer.setPriority(Thread.currentThread().getPriority());
        writer.start();
        ///设置reader为后台运行
        reader.setDaemon(true);
        reader.start();


        try{
            ///使writer线程完成run()后，再执行join()方法后面的代码
            writer.join();
            //中断reader线程
            reader.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
