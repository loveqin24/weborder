package com.epicorweb.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-23
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtils {
    private static Log log = LogFactory.getLog(HttpUtils.class);

    /**
     * 发送http GET请求，并返回http响应字符
     * @param urlstr 完整请求url字符
     * @return
     */
    public static Map doGetRequest(String urlstr) {
        if(StringUtils.isEmpty(urlstr)) return null;
        Map  reMap = new HashMap();
        int rescode = -1;
        String resmsg = "";
        try {
            System.out.println("发送 "+urlstr);
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-Type", "text/html; charset=GB2312");
            //System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "10000"); //jdk1.4换成这个,读操作超            //httpConn.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //httpConn.setReadTimeout(10000);//jdk 1.5换成这个,读操作超
            httpConn.setDoInput(true);
            rescode = httpConn.getResponseCode();
            resmsg = httpConn.getResponseMessage();
            if (log.isDebugEnabled())  {
                log.debug("http url: "+urlstr);
                log.debug("http contetType:"+httpConn.getContentType());
                log.debug("http method:"+httpConn.getRequestMethod()+" code:"+rescode+" msg:"+resmsg);
            }
            log.debug("http post status: rescode="+rescode+" ,msg:"+resmsg);
            if (rescode == 200) {
                BufferedReader bfw = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                //command=MT_RESPONSE&spid=7950&mtstat=ET:0124&mterrcode=100
                //  0
                String line = null;
                StringBuffer bodySb = new StringBuffer();
                while ((line = bfw.readLine()) != null) {
                    String curLine = line.trim();
                    if (StringUtils.isEmpty(curLine))  bodySb.append(curLine);
                }
                log.debug("http result: "+bodySb.toString());
                reMap.put("code",1);
                reMap.put("result",bodySb.toString());
                if (bfw != null) bfw.close();
            } else {
                reMap.put("code",-1);
                reMap.put("result","code="+rescode+",msg="+resmsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "http post status: rescode="+rescode+" ,msg:"+e.toString();
            log.error(msg);
            reMap.put("code",-1);
            reMap.put("result",msg);
        }
        return reMap;
    }

    /**
     * 发送http POST请求，并返回http响应字符
     * @param urlstr 完整请求url字符
     * @return
     */
    public static Map doPostRequest(String urlstr,String body,String charset,String contentType,String soapAction) {
        if(StringUtils.isEmpty(urlstr)) return null;
        Map  reMap = new HashMap();
        int rescode = -1;
        String resmsg = "";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            if(contentType == null) contentType = "xml";
            if(charset == null) {
                charset = "UTF-8";
            }
            if (contentType.equalsIgnoreCase("xml")) {
                httpConn.setRequestProperty("Content-Type", "text/xml; charset="+charset);
            } else if (contentType.equalsIgnoreCase("Soap")) {
                httpConn.setRequestProperty("Content-Type", "text/xml; charset="+charset);
                httpConn.setRequestProperty("SOAPAction", soapAction);
            } else if (contentType.equalsIgnoreCase("Soap12")) {
                httpConn.setRequestProperty("Content-Type", "application/soap+xml; charset="+charset);
            } else if(contentType.equalsIgnoreCase("form")){
                httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //表单方式
            } else {
                httpConn.setRequestProperty("Content-Type", "text/html; charset="+charset);
            }
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(false);
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "10000"); //jdk1.4换成这个,读操作超???
            //httpConn.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //httpConn.setReadTimeout(10000);//jdk 1.5换成这个,读操作超
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            if (StringUtils.isEmpty(body)) {
                body = urlstr.substring(urlstr.indexOf("?")+1,urlstr.length());
            }
            log.debug("http post body: "+body);
            OutputStream out = httpConn.getOutputStream();
            out.write(body.getBytes());
            out.flush();

            rescode = httpConn.getResponseCode();
            resmsg = httpConn.getResponseMessage();
            if (log.isDebugEnabled())  {
                log.debug("http url: "+urlstr);
                log.debug("http contetType:"+httpConn.getContentType());
                log.debug("http method:"+httpConn.getRequestMethod()+" code:"+rescode+" msg:"+resmsg);
            }
            log.debug("http post status: rescode="+rescode+" ,msg:"+resmsg);
            if (rescode == 200) {
                BufferedReader bfw = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
                //command=MT_RESPONSE&spid=7950&mtstat=ET:0124&mterrcode=100
                //  0
                String line = null;
                StringBuffer bodySb = new StringBuffer();
                while ((line = bfw.readLine()) != null) {
                    String curLine = line.trim();
                    if (!StringUtils.isEmpty(curLine))  bodySb.append(curLine);
                }
                log.debug("http result: "+bodySb.toString());
                reMap.put("code",1);
                reMap.put("result",bodySb.toString());
                if (bfw != null) bfw.close();
                if (out != null) out.close();
            } else {
                reMap.put("code",-1);
                reMap.put("result","code="+rescode+",msg="+resmsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "http post status:rescode="+rescode+" ,msg:"+e.toString();
            log.error(msg);
            reMap.put("code",-1);
            reMap.put("result",msg);
        }
        return reMap;
    }

    public static void main(String[] args){
        //String soapUrl = "http://nx.bnet.cn/services/getPortalRequest?streamingno=1&rand=222&getPortalRequest=1";
        String soapUrl = "http://www.bnet.cn/v3.0/getPortalRequest?streamingno=1&rand=222&getPortalRequest=1";
        Map map = doPostRequest(soapUrl,null,null,"soap","getPortalRequest");
        log.debug(map.get("code"));
        log.debug(map.get("result"));
    }
}
