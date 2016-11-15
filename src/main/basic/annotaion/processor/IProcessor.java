package main.basic.annotaion.processor;

/**
 * 注解处理器 接口
 * Created by androidjp on 2016/11/14.
 *
 */
public interface IProcessor {
    String process(String url) throws Exception;
}
