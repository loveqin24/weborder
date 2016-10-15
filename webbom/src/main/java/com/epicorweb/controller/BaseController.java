package com.epicorweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.epicorweb.entity.User;
import com.epicorweb.utils.CommonConstant;

public class BaseController {  
    protected static final String ERROR_MSG_KEY = "errorMsg";  
 
    //① 获取保存在Session中的用户对象  
    protected User getSessionUser(HttpServletRequest request) {  
        return (User) request.getSession().getAttribute(  
                CommonConstant.USER_CONTEXT);  
    }  
     
     //②将用户对象保存到Session中  
    protected void setSessionUser(HttpServletRequest request,User user) {  
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT,  
                user);  
    }  
      
    //③ 获取基于应用程序的url绝对路径  
    public final String getAppbaseUrl(HttpServletRequest request, String url) {  
        Assert.hasLength(url, "url不能为空");  
        Assert.isTrue(url.startsWith("/"), "必须以/打头");  
        return request.getContextPath() + url;  
    }  
      
}  