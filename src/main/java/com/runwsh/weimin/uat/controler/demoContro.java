package com.runwsh.weimin.uat.controler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.runwsh.weimin.model.RequestView;
import okhttp3.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/demo")
public class demoContro {
    private List<RequestView> mapList = new ArrayList<>();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @RequestMapping(value = "/cqw")
    public String cqw() {
        return getClientIp();
    }

    public String getClientIp() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        System.out.println("clientIp 1:{},clientIp 2:{}" + request.getHeader("X-Real-IP") + request.getHeader("X-Forwarded-For"));
        /**
         * 获取客户端原始IP地址
         * 先检查X-Forward-For头信息，如果不存在，则返回remoteAddr
         * @return 客户端原始IP地址
         */
        try {
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
        } catch (Exception e) {
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

    @RequestMapping(value = "/getLoan")
    public String getLoanWx(@RequestParam("area1001") String area1001,
                          @RequestParam("area1002") String area1002,Model model) {
        String ICBCWAPB_B7_ID_1002 = "area_1002=1002; ICBCWAPB_B7_ID=" + area1002 + "; ICBCWAPB_BETA_VER7=beta; JSESSIONID=20330F8C125E75951A403297E8F28F68; area_1002=1002";
        String ICBCWAPB_B7_ID_1001 = "area_1001=1001; ICBCWAPB_B7_ID=" + area1001 + "; ICBCWAPB_BETA_VER7=beta; JSESSIONID=20330F8C125E75951A403297E8F28F68; area_1001=1001";


        // 首次延迟 2 秒，之后每隔 5 秒执行一次（按开始时间计算间隔）
        scheduler.scheduleAtFixedRate(() -> {
            requestArea(ICBCWAPB_B7_ID_1001, area1001);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            requestArea(ICBCWAPB_B7_ID_1002, area1002);
        }, 2, 5 * 60, TimeUnit.SECONDS);
        return "welcome";
    }

    public void requestArea(String areaStr, String ICBCWAPB_B7_ID) {
        if (mapList.size()>500){
            mapList.clear();
        }
        StringBuilder stringBuilder = new StringBuilder();
        RequestView loanInfo = new RequestView();
        try {
            Response response1002 = null;
            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    // 执行请求前，获取连接信息
                    Connection connection = chain.connection();
                    if (connection != null) {
                        // 获取路由信息
                        Route route = connection.route();
                        InetSocketAddress socketAddress = route.socketAddress();

                        // 打印服务器 IP 和端口
                        System.out.println("=== 服务器来源信息 ===" + socketAddress);
                        System.out.println("服务器 IP 地址: " + socketAddress.getAddress().getHostAddress());
                        System.out.println("服务器端口号: " + socketAddress.getPort());
                        System.out.println("使用的协议: " + connection.protocol());
                    }
                    return chain.proceed(chain.request());
                }
            }).build();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"data\":{\"bizParam\":\"7b397adba69c4793bf6c83e250c9be1b\"},\"terminalType\":\"0\"}");
            Request request1002 = new Request.Builder()
                    .url("https://mywap2.icbc.com.cn/ICBCWAPBankB7NEW/api/loan/hz1/wechat/homepage/init")
                    .post(body)
                    .addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 26_0_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.75(0x18004b52) NetType/4G Language/zh_CN miniProgram/wxc575845fbd5bac33")
                    .addHeader("Accept", "application/json, text/plain, */*")
                    .addHeader("ICBCWAPB_B7_ID", ICBCWAPB_B7_ID)
                    .addHeader("Sec-Fetch-Site", "same-origin")
                    .addHeader("Accept-Language", "zh-CN,zh-Hans;q=0.9")
                    .addHeader("Sec-Fetch-Mode", "cors")
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .addHeader("Origin", "https://mywap2.icbc.com.cn")
                    .addHeader("Referer", "https://mywap2.icbc.com.cn/ICBCWAPBankB7NEW/")
                    .addHeader("ICBCWAPB-B7-COOKIES", "")
                    .addHeader("X-Fee-Request", "axios")
                    .addHeader("Sec-Fetch-Dest", "empty")
                    .addHeader("Cookie", areaStr)
                    .build();
            response1002 = client.newCall(request1002).execute();
            System.out.println("======================11111111111response1111111111============================");
            Response finalResponse1002 = response1002;

            finalResponse1002.headers("Set-Cookie").forEach(name -> {
                stringBuilder.append(name).append("\n");
            });
            loanInfo.setCookie(finalResponse1002.headers("Set-Cookie").toString());
            JSONObject jsonObject = JSONObject.parseObject(response1002.body().string());
            loanInfo.setErrorMsg(jsonObject.getString("errorMsg"));
            loanInfo.setJson(JSON.toJSONString(jsonObject));
            loanInfo.setSuccess(jsonObject.getString("success"));
        } catch (Exception e) {

        }
        mapList.add(loanInfo);
    }

    @RequestMapping(value = "/showLoan")
    public String showLoanWxInfo(Model model){
        model.addAttribute("list",mapList);
        return "index";
    }
}
