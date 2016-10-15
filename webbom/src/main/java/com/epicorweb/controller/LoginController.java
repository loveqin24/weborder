package com.epicorweb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epicorweb.services.impl.UserServiceImpl;
import com.epicorweb.utils.CommonConstant;
import com.epicorweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epicorweb.entity.User;
import com.epicorweb.services.UserService;

@Controller  
public class LoginController extends BaseController {
@Autowired
    @Resource(name ="userService")
   private UserServiceImpl userService;

    @RequestMapping("/index")
    public String indexMain(){
        return "index";
    }

    @RequestMapping("/login")
    public String show(){
        return "login";
    }
    //① 用户登录  
     @RequestMapping("/doLogin")  
    public ModelAndView login(HttpServletRequest request, User user) {  
        User dbUser = userService.getUserByUserName(user.getUsername());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/login.jsp");
        if(dbUser == null){
            mav.addObject("errorMsg", "用户名不存在");
          }else if(!dbUser.getPassword().equals(user.getPassword())){
            mav.addObject("errorMsg","用户密码不正确");
          }else{

            setSessionUser(request,dbUser);
            String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);

            //如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
            if(StringUtils.isEmpty(toUrl)){
                toUrl = "index";
            }
            mav.setViewName("redirect:"+toUrl);
          }
        return mav;
//         ModelAndView mav = new ModelAndView();
//         mav.setViewName("redirect:index");
//         return mav;
    }  
 
    //② 登录注销  
    @RequestMapping("/doLogout")  
    public String logout(HttpSession session) {  
        //session.removeAttribute(USER_CONTEXT);  
        return "forward:/index.jsp";  
    }  
}  