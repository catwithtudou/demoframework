package org.blue.demoframework.util;

import com.sun.istack.internal.NotNull;

/**
 * @author 郑煜
 * @Title: CastUtil
 * @ProjectName demoframework
 * @Description: 类型转换工具类
 * @date 2019/3/12下午 10:08
 */
public class CastUtil {

    /**
    　　* @Description:将字符串转换为int值
    　　* @param :text
    　　* @return :int
    　　*/
    public static int castInt(@NotNull String text){
        return castInt(text,0);
    }

    /**
    　　* @Description:将字符串转换为int值,若不合法则返回预设值
    　　* @param :text
    　　* @param :defaultValue
    　　* @return :int
    　　*/
    public static int castInt(@NotNull String text,@NotNull int defaultValue){
        int res;
        try{
            res=Integer.parseInt(text);
        }catch (NumberFormatException e){
            res=defaultValue;
        }
        return res;
    }

    /**
    　　* @Description:将字符串转换为long值
    　　* @param :text
    　　* @return :long
    　　*/
    public static  long castLong(@NotNull String text){
        return castLong(text,0L);
    }

    /**
    　　* @Description:将字符串转为long值
    　　* @param :text
    　　* @param :defaultValue
    　　* @return :long
    　　*/
    public static long castLong(@NotNull String text, @NotNull long defaultValue){
        long res;
        try{
            res=Long.parseLong(text);
        }catch (NumberFormatException e ){
            res=defaultValue;
        }
        return res;
    }

}
