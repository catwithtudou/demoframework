package org.blue.demoframework.util;

import java.io.*;

/**
 * @author 郑煜
 * @Title: StreamUtil
 * @ProjectName demoframework
 * @Description: 流工具类
 * @date 2019/3/13上午 12:09
 */
public class StreamUtil {

    /**
    　　* @Description:将数据写入输出流
    　　* @param :out
    　　* @param :text
    　　* @return :void
    　　*/
    public static  void writeStream(OutputStream out,String text){
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        try {
            writer.write(text);
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
    　　* @Description:从输入流读取数据
    　　* @param :in
    　　* @return :String
    　　*/
    public static String readStream(InputStream in){
        String res=null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line;
        try {
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            res=sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }
}
