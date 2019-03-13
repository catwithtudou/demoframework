package org.blue.demoframework.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郑煜
 * @Title: Component
 * @ProjectName demoframework
 * @Description: 标注一个组件
 * @date 2019/3/13上午 01:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
