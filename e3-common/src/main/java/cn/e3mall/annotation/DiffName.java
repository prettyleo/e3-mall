package cn.e3mall.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FileName: DiffName
 * DESCRIPTION: 属性名不同的对象拷贝, 需要用到, 用于注明不同的属性名
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DiffName {

    String value();
}
