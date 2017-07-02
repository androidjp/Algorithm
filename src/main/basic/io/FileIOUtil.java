package main.basic.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileIOUtil {

    /**
     * 以字节为单位读取文件内容
     *
     * @param filePath：需要读取的文件路径
     */
    public static String readFileByByte(String filePath) {
        File file = new File(filePath);
        // InputStream:此抽象类是表示字节输入流的所有类的超类。  
        InputStream ins = null;
        StringBuilder sb = null;
        try {
            // FileInputStream:从文件系统中的某个文件中获得输入字节。  
            ins = new FileInputStream(file);
            int temp;
            sb = new StringBuilder(256);
            byte[] bytes = new byte[1024];
            // read():从输入流中读取数据的下一个字节。  
            while ((temp = ins.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, temp));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 以字符为单位读取文件内容
     *
     * @param filePath 文件名
     */
    public static void readFileByCharacter(String filePath) {
        File file = new File(filePath);
        // FileReader:用来读取字符文件的便捷类。  
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int temp;
            while ((temp = reader.read()) != -1) {
                if (((char) temp) != '\r') {
                    System.out.print((char) temp);
                }
            }

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 以行为单位读取文件内容
     *
     * @param filePath
     */
    public static void readFileByLine(String filePath) {
        File file = new File(filePath);
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。  
        BufferedReader buf = null;
        try {
            // FileReader:用来读取字符文件的便捷类。  
            buf = new BufferedReader(new FileReader(file));
            // buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));  
            String temp = null;
            while ((temp = buf.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    /**
     * 以字节为单位copy文件内容
     *
     * @param filePath
     * @param pastePath ：需要读取的文件路径
     */
    public static void copyFileByByte(String filePath, String pastePath) {
        File file = new File(filePath);

        if (!file.exists())
            throw new IllegalArgumentException("文件:" + filePath + "不存在");
        if (file.isDirectory()) {
            throw new IllegalArgumentException("文件:" + filePath + "不是文件");
        }
        // InputStream:此抽象类是表示字节输入流的所有类的超类。  
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // FileInputStream:从文件系统中的某个文件中获得输入字节。  
            ins = new FileInputStream(file);
            outs = new FileOutputStream(pastePath);
            byte[] buf = new byte[8 * 1024];
            int temp;
            // read():从输入流中读取数据的下一个字节。  
            while ((temp = ins.read(buf, 0, buf.length)) != -1) {
                outs.write(buf, 0, temp);
                outs.flush();
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (ins != null)
                    ins.close();

                if (outs != null)
                    outs.close();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * 以字符为单位读写文件内容
     *
     * @param filePath
     * @param pastePath
     */
    public static void copyFileByCharacter(String filePath, String pastePath) {
        File file = new File(filePath);
        // FileReader:用来读取字符文件的便捷类。  
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(file);
            writer = new FileWriter(pastePath);
            int temp;
            while ((temp = reader.read()) != -1) {
                writer.write((char) temp);
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (reader != null && writer != null) {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 以行为单位读写文件内容
     *
     * @param filePath
     */
    public static void copyFileByLine(String filePath, String pastePath) {
        File file = new File(filePath);
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。  
        BufferedReader bufReader = null;
        BufferedWriter bufWriter = null;
        try {
            // FileReader:用来读取字符文件的便捷类。  
            bufReader = new BufferedReader(new FileReader(file));
            bufWriter = new BufferedWriter(new FileWriter(pastePath));
            // buf = new BufferedReader(new InputStreamReader(new  
            // FileInputStream(file)));  
            String temp = null;
            while ((temp = bufReader.readLine()) != null) {
                bufWriter.write(temp + "\n");
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (bufReader != null && bufWriter != null) {
                try {
                    bufReader.close();
                    bufWriter.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    /**
     * 使用Java.nio ByteBuffer字节将一个文件输出至另一文件
     *
     * @param filePath
     */
    public static void copyFileByBybeBuffer(String filePath, String pastePath) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            // 获取源文件和目标文件的输入输出流    
            in = new FileInputStream(filePath);
            out = new FileOutputStream(pastePath);
            // 获取输入输出通道  
            FileChannel fcIn = in.getChannel();
            FileChannel fcOut = out.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // clear方法重设缓冲区，使它可以接受读入的数据  
                buffer.clear();
                // 从输入通道中将数据读到缓冲区  
                int r = fcIn.read(buffer);
                if (r == -1) {
                    break;
                }
                // flip方法让缓冲区可以将新读入的数据写入另一个通道    
                buffer.flip();
                fcOut.write(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null && out != null) {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取某目录下的所有文件
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists())
            throw new IllegalArgumentException("目录：" + dir + "不存在");
        if (!dir.isDirectory())
            throw new IllegalArgumentException(dir + "不是目录");
        File[] files = dir.listFiles();
        for (File item :
                files) {
            System.out.println(dir + "//" + item);
            if (item.isDirectory())///递归获取目录内内容
                listDirectory(item);
        }
    }

    /**
     * 使用RandomAccessFile读文件
     */
    public static void readByRandomAccessFile(String filePath) throws IOException{
        File file  = new File(filePath);
        if (!file.exists())
            file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        int max = 0x7fffffff;
        char text ='你';
        System.out.println("max本身为："+max+"，而Integer.MAXVALUE为："+ Integer.MAX_VALUE);
//        raf.write(max>>>24);
//        raf.write(max>>>16);
//        raf.write(max>>>8);
//        raf.write(max);
        raf.writeChar(text);
        raf.close();
        ///
        raf = new RandomAccessFile(file,"r");
        System.out.println(raf.readChar());
        raf.close();
    }

}