package main.basic.annotaion.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：表示"列"
 * Created by androidjp on 2016/11/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value() default "";// 字段值
    boolean nullable() default true;///是否可空
    boolean autoIncrement() default false;//是否自增
    int length() default -1;///列（字段） 长度
}
