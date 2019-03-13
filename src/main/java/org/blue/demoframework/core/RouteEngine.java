package org.blue.demoframework.core;

import org.blue.demoframework.annotation.HttpMethod;
import org.blue.demoframework.annotation.RequestMapper;
import org.blue.demoframework.bean.RouteInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郑煜
 * @Title: RouteEngine
 * @ProjectName demoframework
 * @Description: 路由引擎
 * @date 2019/3/13 上午 11:20
 */
public class RouteEngine {

    private static RouteEngine instance = null;

    private BeanFactory beanFactory=null;

    /**
     * 用来存放method和uri对处理器的映射
     */
    private Map<RouteInfo,Handle> routeMap;

    public static RouteEngine getInstance(){
        if(instance==null){
            synchronized(RouteEngine.class){
                if(instance==null){
                    instance=new RouteEngine();
                }
            }
        }
        return instance;
    }

    private RouteEngine(){
        init();
    }

    private void init(){
        beanFactory=BeanFactory.getInstance();
        routeMap=new HashMap<>(20);
        loadRoute();
    }

    /**
     * @Description:加载路由
     */
    private  void loadRoute(){
        Map<Class<?>,Object> controllerMap=beanFactory.getControllerMap();
        String prefix="";
        for(Map.Entry<Class<?>,Object> entry:controllerMap.entrySet()){
            Class<?> cls=entry.getKey();
            Object controller=entry.getValue();

            RequestMapper controllerMapper=cls.getAnnotation(RequestMapper.class);
            if(controllerMapper!=null ){
                controllerMapper.value();
                prefix=controllerMapper.value();
            }

            Method[] methods=cls.getMethods();
            for(Method method:methods){
                RequestMapper mapper=method.getAnnotation(RequestMapper.class);
                if(mapper!=null){
                    String uri=mapper.value();
                    HttpMethod httpMethod=mapper.method();
                    RouteInfo routeInfo=new RouteInfo(httpMethod,uri);
                    Handle handle=new Handle(controller,method);
                    routeMap.put(routeInfo,handle);
                }
            }
        }
    }

    public Handle getHandle(RouteInfo route){
        return routeMap.get(route);
    }
}
