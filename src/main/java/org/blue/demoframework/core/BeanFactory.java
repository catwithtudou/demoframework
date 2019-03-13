package org.blue.demoframework.core;

import org.blue.demoframework.annotation.Autowired;
import org.blue.demoframework.annotation.Component;
import org.blue.demoframework.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 郑煜
 * @Title: BeanFactory
 * @ProjectName demoframework
 * @Description: 对象工厂
 * @date 2019/3/13上午 10:41
 */
public class BeanFactory {

    /**
     *单例模式的单例对象
     */
    private static BeanFactory beanFactory;

    /**
     *类加载器
     */
    private ClassLoader classLoader;

    /**
     *组件集合 即所有含@Controller注解和@Component注解的类
     */
    private Set<Class<?>> componentSet;

    /**
     *用来给controller类和对象提供映射的集合
     */
    private Map<Class<?>,Object> controllerMap;

    /**
     *同上 普通component
     */
    private Map<Class<?>,Object> componentMap;

    /**
     * 获得单例
     */
    public static BeanFactory getInstance(){
        if(beanFactory==null){
            synchronized (BeanFactory.class){
                if(beanFactory==null){
                    beanFactory=new BeanFactory();
                }
            }
        }
        return beanFactory;
    }

    public Map<Class<?>, Object> getControllerMap() {
        return controllerMap;
    }

    private BeanFactory(){
        componentMap=new HashMap<>();
        controllerMap=new HashMap<>();
        componentSet =new HashSet<>();
        classLoader=ClassLoader.getInstance();
        init();
    }

    /**
     * 初始化 创建所有类的对象
     */
    private void init(){
        Set<Class<?>> classSet=classLoader.getClassSet();
        //先加载类的集合创造映射
        for(Class<?> cls:classSet){
            Annotation[] annotations=cls.getAnnotations();
            for(Annotation annotation : annotations ){
                Class<? extends Annotation> an=annotation.annotationType();
                if(an.equals(Component.class)||an.isAnnotationPresent(Component.class)){
                    Object obj= ReflectUtil.newInstance(cls);
                    componentMap.put(cls,obj);
                    componentSet.add(cls);
                    if(!an.equals(Component.class)){
                        controllerMap.put(cls,obj);
                    }
                }
            }
        }

        //再对每个component里的成员参数赋值
        for (Class<?> cls:componentSet){
            Field[] fields=cls.getDeclaredFields();
            for(Field field:fields){
                Autowired autowired=field.getAnnotation(Autowired.class);
                if(autowired!=null){
                    Object target=componentMap.get(field.getType());
                    if(target!=null){
                        Object obj=componentMap.get(cls);
                        ReflectUtil.setFieldValue(field,obj, target);
                    }else{
                        throw new RuntimeException("this component '"+field.getName()+" is undefined");
                    }
                }
            }
        }
    }

    public <T> T getBean(Class<T> cls){
        T t=(T) componentMap.get(cls);
        if(t!=null){
            return t;
        }
        throw new RuntimeException("bean not found");
    }
}
