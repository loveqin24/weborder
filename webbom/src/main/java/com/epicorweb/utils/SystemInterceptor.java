package com.epicorweb.utils;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * To change this template use File | Settings | File Templates.
 */
public class SystemInterceptor implements HandlerInterceptor {
    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("----------->request.getServerName():"+request.getServerName());
            logger.debug("----------->request.getPathInfo():"+request.getPathInfo());
            logger.debug("----------->request.getContextPath():"+request.getContextPath());
            logger.debug("----------->request.getRequestURI():"+request.getRequestURI());
            logger.debug("----------->request.getRequestURL():"+request.getRequestURL());
            if(modelAndView != null)
             logger.debug("----------->view:"+modelAndView.getViewName());
        }
    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if(logger.isDebugEnabled())
            logger.debug("----------->After completion handle");
    }
}
