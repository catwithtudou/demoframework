package org.blue.demoframework.core;

import org.blue.demoframework.annotation.HttpMethod;
import org.blue.demoframework.bean.DemoContext;
import org.blue.demoframework.bean.RouteInfo;
import org.blue.demoframework.util.StreamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: DispatcherServlet
 * @ProjectName demoframework
 * @Description: 单一入口Servlet
 * @date 2019/3/13 上午 11:38
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet{

    private PropsLoader propsLoader=null;
    private ClassLoader classLoader=null;
    private BeanFactory beanFactory=null;
    private RouteEngine routeEngine=null;

    @Override
    public void init(){
        PropsLoader.init(getServletContext());
        propsLoader=PropsLoader.getInstance();
        classLoader=ClassLoader.getInstance();
        beanFactory=BeanFactory.getInstance();
        routeEngine=RouteEngine.getInstance();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String res;

        HttpMethod method=HttpMethod.valueOf(req.getMethod());
        String[] uriInfo=req.getRequestURI().split("\\?");
        RouteInfo routeInfo=new RouteInfo(method,uriInfo[0]);
        if(uriInfo.length>1){
            routeInfo.setUriParam(uriInfo[1]);
        }

        Handle handle=routeEngine.getHandle(routeInfo);

        if(handle==null){
            res="404 NOT FOUND";
        }else{
            try {
                DemoContext context=new DemoContext(req,resp,routeInfo);
                res=handle.invoke(context);
            }catch (Exception e){
                res=e.getMessage();
            }
        }

        if(res!=null){
            StreamUtil.writeStream(resp.getOutputStream(),res);
        }else {
            resp.getOutputStream().close();
        }
    }
}
