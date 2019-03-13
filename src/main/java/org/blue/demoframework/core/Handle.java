package org.blue.demoframework.core;

import com.google.gson.Gson;
import org.blue.demoframework.bean.DemoContext;
import org.blue.demoframework.bean.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @author 郑煜
 * @Title: Handle
 * @ProjectName demoframework
 * @Description: 封装过的处理器
 * @date 2019/3/13 上午 11:22
 */
public class Handle {

    /**
     * 全局统一的json处理库
     */
    private static final Gson GSON=new Gson();

    /**
     * 用来处理请求的方法
     */
    private Method method;

    /**
     * 该方法所属的controller
     */
    private Object controller;

    Handle(Object controller,Method method){
        this.controller=controller;
        this.method=method;
    }

    /**
     * @Description:根据上下文执行方法
     * @param context 框架的上下文
     * @return 如果有返回那么输出到网络流
     */
    String invoke(DemoContext context){
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response=context.getResponse();

        Enumeration<String> params=request.getParameterNames();
        while(params.hasMoreElements()){
            String key=params.nextElement();
            String value=request.getParameter(key);
            context.putParam(key,value);
        }

        ResponseEntity res=null;
        try{
            res=(ResponseEntity) method.invoke(controller,context);
        }catch (IllegalAccessException| InvocationTargetException e){
            e.printStackTrace();
        }
        if(res!=null){
            return GSON.toJson(res);
        }
        return null;
    }
}
