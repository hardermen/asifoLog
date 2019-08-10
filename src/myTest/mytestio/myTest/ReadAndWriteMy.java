package myTest.mytestio.myTest;

import java.io.*;

public class ReadAndWriteMy {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1.建立联系---选路
        File src = new File("E:\\test\\1908\\shediao.txt");
        File tar = new File("E:\\test\\1908\\shediaoshuchu.txt");
        //2.选择流--读的方式
        try {
            FileInputStream fi = new FileInputStream(src);
            FileWriter fw = new FileWriter(tar);
            //2.1如果不使用下面的输出缓冲流，输入输出完成时间是243秒，使用大约是130秒，一倍！
            BufferedWriter bw = new BufferedWriter(fw);
            //3.建卡车，这里为了效率和方便性，直接用缓冲流包装一下，车好了！,下面是先把文件字节转换成字符流，然后用缓冲流封装！
            BufferedReader bf = new BufferedReader(new InputStreamReader(fi));
            String str = "";
            while ((str = bf.readLine()) != null) {
                bw.write(str);
                bw.newLine();
            }
            fi.close();
            fw.close();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
