/**
 * HTTP HyperText Transport Protocol
 * 1. 适用于分布式超媒体信息系统的应用层协议
 * 2. 1990 年提出的
 * 特点：
 * 1. 支持C/S模式
 * 2. 简单快速（请求方式 + 请求路径）
 * 3. 灵活
 * 4. 无连接
 * 5. 无状态
 *
 * [HTTP请求报文结构：]
 * method URI version\r\n
 * header: value \r\n
 * .
 * .
 * .
 * header: value \r\n
 * \r\n
 * request data
 *
 * [HTTP响应保温结构：]
 * version statusCode Message \r\n
 * header: value \r\n
 * .
 * .
 * header: value \r\n
 * \r\n
 * response data
 *
 *
 *
 * Android HttpClient 与 Java/Android通用的 HttpURLConnection的区别：
 * 1. HttpClient 是 Apache Jakarta Common下的子项目，提供高校的支持HTTP协议的客户端变成工具包
 * 2. URL（Uniform Resource Locator）统一资源定位符
 * 3. 而HttpURLConnection是用于读取和写入URL应用的资源的工具类
 *
 * Created by androidjp on 2017/1/23.
 */
package main.network.http;