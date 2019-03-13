package org.blue.demoframework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 郑煜
 * @Title: ReflectUtil
 * @ProjectName demoframework
 * @Description: 反射工具类
 * @date 2019/3/12下午 11:52
 */
public class ReflectUtil {

    /**
    　　* @Description:使用反射获得一个类的对象,该类必须有一个无参构造器
    　　* @param : cls
    　　* @return : Object
    　　*/
    public static Object newInstance(Class<?> cls){
        Object o=null;
        try{
            o=cls.newInstance();
        }catch (InstantiationException|IllegalAccessException e){
            e.printStackTrace();
        }
        return o;
    }

    /**
    　　* @Description:使用反射获得一个类的对象,该类必须有一个以一个字符串为参数的构造器
    　　* @param :cls
    　　* @param :value
    　　* @return :Object
    　　*/
    public static Object newInstance(Class<?> cls,String value){
        Object object=null;
        try{
            Constructor constructor = cls.getConstructor(String.class);
            object = constructor.newInstance(value);
        }catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            object=-1;
        }
        return object;
    }

    /**
    　　* @Description:检验一个对象时否为包装类(Integer long double)
    　　* @param :cls
    　　* @return :boolean
    　　*/
    public static boolean isPrimitive(Class<?> cls){
        if(!cls.isPrimitive()){
            switch(cls.getName()){
                case "java.lang.Integer":
                    return true;
                case "java.lang.Long":
                    return true;
                case "java.lang.Double":
                    return true;
                case "java.lang.Float":
                    return true;
                case "java.lang.Boolean":
                    return true;
                case "java.lang.Character":
                    return true;
                case "java.lang.String":
                    return true;
                    default:
                        return false;
            }
        }
        return true;
    }

    /**
    　　* @Description:获得值类型的包装类,若传参不是值类型则返回原类型
    　　* @param :cls
    　　* @return :Class<?>
    　　*/
    public static  Class<?> getNormalClass(Class<?> cls){
        String name=cls.getName();
        switch (name){
            case "int":
                return Integer.class;
            case "long":
                return Long.class;
            case "double":
                return Double.class;
            case "float" :
                return Float.class;
            case "char":
                return Character.class;
            case "boolean":
                return Boolean.class;
            default:
                return cls;
        }
    }

    /**
     * 使用反射设置某对象的参数的值
     * @param field 参数
     * @param obj 对象
     * @param value 值
     */
    public static void setFieldValue(Field field, Object obj, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
