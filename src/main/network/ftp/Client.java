package main.network.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by androidjp on 2017/1/19.
 */
public class Client {
    FTPClient ftpClient = null;

    public void start(){
        try{
            ftpClient =new FTPClient();
            ////连接到指定FTP服务器
            ftpClient.connect(InetAddress.getByName("host address"));
            /// 使用用户名+密码登录FTP服务器
            ftpClient.login("name","password");
            //检测返回的字符串是否包含250
            ///250 表示"行为完成"
            if (ftpClient.getReplyString().contains("250")){
                //设置文件类型
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);///默认使用ASCII编码，这里设置为二进制文件
                ///定义一个缓冲区
                BufferedInputStream buffIn = null;
                buffIn =  new BufferedInputStream(new FileInputStream("本地文件的绝对路径"));
                ///设置客户端模式（C主动连接S，用20端口）
                ftpClient.enterLocalPassiveMode();
                ///存储文件
                boolean res  = ftpClient.storeFile("远程文件路径",buffIn);
                ///关闭缓冲区
                buffIn.close();
                ///登出
                ftpClient.logout();
                ///断开连接
                ftpClient.disconnect();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
