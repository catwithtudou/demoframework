package org.blue.demoframework.bean;

import org.blue.demoframework.annotation.HttpMethod;

import java.util.Objects;

/**
 * @author 郑煜
 * @Title: RouteInfo
 * @ProjectName demoframework
 * @Description: 路由信息
 * @date 2019/3/13上午 01:24
 */
public class RouteInfo {

    private String uri;

    private HttpMethod method;

    private String uriParam;

    private String getUri(){return uri;}

    public String getUriParam() {
        return uriParam;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setUriParam(String uriParam) {
        this.uriParam = uriParam;
    }

    public RouteInfo(HttpMethod method,String uri){
        this.method = method;
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o==null || getClass() !=o.getClass()){
            return false;
        }
        RouteInfo routeInfo=(RouteInfo) o;
        return Objects.equals(uri,routeInfo.uri)&&
                method==routeInfo.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri,method);
    }
}
