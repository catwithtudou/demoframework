package org.blue.demoframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郑煜
 * @Title: RequestMapper
 * @ProjectName demoframework
 * @Description: 标注Controller里的方法是路由的处理方法
 * @date 2019/3/13上午 01:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestMapper {
    String value();
    HttpMethod method() default HttpMethod.GET;
}
