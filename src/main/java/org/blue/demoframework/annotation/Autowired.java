package org.blue.demoframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郑煜
 * @Title: Autowired
 * @ProjectName demoframework
 * @Description: 标注这个对象需要被框架注入
 * @date 2019/3/13上午 01:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
