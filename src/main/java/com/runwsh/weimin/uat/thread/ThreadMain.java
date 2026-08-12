package com.runwsh.weimin.uat.thread;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author weimin
 * 继承Thread类
 * @date 2019/12/23 10:05
 */

public class ThreadMain{

//    public static void main(String[] args) {
//        threadMethod();
//    }
    public static void main1(String[] args) {
        Integer a=1000,b=1000;
        System.out.println(a==b);

        int a1=1000,b1=1000;
        System.out.println(a==b1);

        Integer c=127,d=127;
        System.out.println(c==d);
    }

    /**
     * Thread 创建线程
     */
    private static void threadMethod() {
        // 创建线程对象
        Thread thread = new Thread(()->{ System.out.println("线程启动了");});
        // 启动线程
        thread.start();
    }

    public String getClientIp() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        System.out.println("clientIp 1:{},clientIp 2:{}"+request.getHeader("X-Real-IP")+request.getHeader("X-Forwarded-For"));
        /**
         * 获取客户端原始IP地址
         * 先检查X-Forward-For头信息，如果不存在，则返回remoteAddr
         * @return 客户端原始IP地址
         */
        try{
            String xForwardedFor = getLastXForwardFor(request);
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                // 如果有多个IP，获取第一个IP
                String[] ips = xForwardedFor.split(",");
                String ip = ips[0].trim();
                if (ip == null || ip.isEmpty()) {
                    ip = request.getRemoteAddr();
                }
                return ip;
            }
            // 如果不存在X-Forward-For头信息，则返回remoteAddr
            return request.getRemoteAddr();
        }catch (Exception e){
            // 获取客户端原始IP地址失败
        }
        return request.getHeader("X-Forwarded-For");
    }

    public static String getLastXForwardFor(HttpServletRequest request) {
        // 获取所有X-Forwarded-For头信息
        Enumeration<String> headers = request.getHeaders("X-Forwarded-For");
        if (headers == null) {
            return null;
        }
        // 获取最后一个X-Forwarded-For头信息
        String header = null;
        while (headers.hasMoreElements()) {
            header = headers.nextElement();
        }
        return header;
    }


    public static void main(String[] args) {
        float f = 0.1f;          // 0.1在float中无法精确表示，存储为最近的近似值
        double d = (double) f;   // 强制转换
        System.out.println();
        System.out.println(String.valueOf(f));
    }

}
