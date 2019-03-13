package org.blue.demoframework.core;

import com.sun.org.apache.bcel.internal.util.ClassSet;
import org.blue.demoframework.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 郑煜
 * @Title: ClassLoader
 * @ProjectName demoframework
 * @Description: 类加载器
 * @date 2019/3/13上午 10:02
 */
public class ClassLoader {

    /**
    　　* @Description:单例
    　　*/
    private  static ClassLoader singleton;

    /**
    　　* @Description:包下面所有类的集合
    　　*/
    private Set<Class<?>> classSet;

    private static final String PROTOCOL_TYPE="file";

    private static final String PACKAGE_NAME="packageName";

    private static final String POINT=".";

    private static final String END_WITH_CLASS=".class";

    private static final String EMPTY_STRING="";

    /**
    　　* @Description:单例
    　　*/
    public static ClassLoader getInstance(){
        if(singleton==null){
            synchronized (ClassLoader.class){
                if(singleton == null){
                    singleton=new ClassLoader(PropsLoader.getInstance());
                }
            }
        }
        return singleton;
    }

    /**
    　　* @Description:不允许使用任何方式调用无参构造器
    　　*/
    private ClassLoader() throws IllegalAccessException{
        throw new IllegalAccessException();
    }


    private ClassLoader(PropsLoader propsLoader){
        load(propsLoader);
    }

    /**
    　　* @Description:包扫描
    　　* @param :propsLoader 配置加载器
    　　*/
    private void load(PropsLoader loader){
        classSet = new HashSet<>();

        String packageName=loader.getString(PACKAGE_NAME);

        try{
            Enumeration<URL> resources=Thread.currentThread().getContextClassLoader().
                    getResources(packageName.replaceAll("\\.","/"));
            while(resources.hasMoreElements()){
                URL target=resources.nextElement();
                String protocol=target.getProtocol();
                if(protocol.equalsIgnoreCase(PROTOCOL_TYPE)){
                    String packagePath=target.getPath();
                    loadClass(packageName,packagePath);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Set<Class<?>> getClassSet() {
        return classSet;
    }

    /**
    　　* @Description:加载类
    　　* @param :packageName
    　　* @return :packagePath
    　　*/
    private void loadClass(String packageName, String packagePath){
        File[] files=new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().endsWith(END_WITH_CLASS);
            }
        });
        if(files!=null){
            for(File file:files){
                String fileName=file.getName();
                if(file.isDirectory()){
                    String subPackageName=packageName+POINT+fileName;
                    String subPackagePth=packagePath+File.pathSeparator + fileName;
                    loadClass(subPackageName,subPackagePth);
                }else{
                    Class<?> cls=null;
                    try{
                        String className= StringUtil.append(packageName,POINT,fileName).
                                replaceAll(END_WITH_CLASS,EMPTY_STRING);
                        cls=Class.forName(className);
                    }catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    if(cls!=null){
                        classSet.add(cls);
                    }
                }
            }
        }
    }
}
