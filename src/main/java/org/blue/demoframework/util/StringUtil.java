package org.blue.demoframework.util;

/**
 * @author 郑煜
 * @Title: StringUtil
 * @ProjectName demoframework
 * @Description: 字符串工具类
 * @date 2019/3/13上午 12:07
 */
public class StringUtil {

    /**
    　　* @Description:判断字符串是否为空
    　　* @param :String
    　　* @return :boolean
    　　*/
    public static boolean isEmpty(String text){
        return text==null||"".equals(text);
    }

    /**
    　　* @Description:将字符串数组连接为字符串
    　　* @param :array
    　　* @return :String
    　　*/
    public static String append(String... array){
        StringBuilder sb=new StringBuilder();
        for(String str:array){
            sb.append(str);
        }
        return sb.toString();
    }
}
