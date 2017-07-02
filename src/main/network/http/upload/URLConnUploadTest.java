package main.network.http.upload;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * 使用HttpURLConnection 装载url路径与数据，post 数据上传
 * Created by androidjp on 2017/1/23.
 */
public class URLConnUploadTest {

    /**
     * 文件上传
     *
     * @param hostPath      服务器路径
     * @param fileName 本地文件绝对路径
     */
    public static String upload(String hostPath, String fileName) throws IOException {
        String BOUNDARY = UUID.randomUUID().toString();
        URL url = new URL(hostPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setReadTimeout(3000);//从服务器读取数据超时为：3s
        conn.setDoInput(true);//允许读取
        conn.setDoOutput(true);//允许写入
        conn.setUseCaches(false);///不允许缓存
        conn.setRequestProperty("connection", "keep-alive");///设置：保持长连接
        conn.setRequestProperty("Charsert", "UTF-8");///设置字符编码格式为UTF-8
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);///设置文件类型

        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        if (fileName != null && fileName.length() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("--").append(BOUNDARY).append("\r\n").append("Content-Disposition: form-data;name=\"file\"; filename=\"")
                    .append(fileName).append("\"").append("\r\n");
            sb.append("Content-Type: application/octet-stream: charset=").append("\r\n")
                    .append("\r\n");
            ///开始写入
            dos.write(sb.toString().getBytes("utf-8"));///以默认的java编码方式：utf-16be 解析的字节序列
            ///读文件，边读边写
            InputStream is = new FileInputStream(fileName);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                dos.write(buffer, 0, len);
            }
            is.close();
            dos.write(new String("\r\n").getBytes());
        }

        ///请求结束标志
        byte[] end_data = new String("--" + BOUNDARY + "--\r\n").getBytes();
        dos.write(end_data);

        ///刷新
        dos.flush();

        //获取响应吗
        int res = conn.getResponseCode();
        InputStream in = null;
        StringBuilder sb2 = null;
        if (res == 200) {
            ///读取数据
            in = conn.getInputStream();
            int ch;
            sb2 = new StringBuilder();
            while ((ch = in.read()) != -1) {
                sb2.append((char) ch);
            }
        }

        return in == null ? null : sb2.toString();
    }
}
