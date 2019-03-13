package org.blue.demoframework.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郑煜
 * @Title: DemoContext
 * @ProjectName demoframework
 * @Description: 框架上下文
 * @date 2019/3/13上午 09:56
 */
public class DemoContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String,String> paramMap;

    private RouteInfo routeInfo;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void putParam(String key,String value){
       this.paramMap.put(key, value);
    }

    public String getParam(String key){
        return this.paramMap.get(key);
    }


    public DemoContext(HttpServletRequest request,HttpServletResponse response,RouteInfo routeInfo){
        this.request = request;
        this.response = response;
        this.routeInfo=routeInfo;
        this.paramMap = new HashMap<>();
    }
}
