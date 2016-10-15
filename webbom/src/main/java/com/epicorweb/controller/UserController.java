package com.epicorweb.controller;

import com.epicorweb.entity.User;
import com.epicorweb.services.impl.UserServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/user"})
public class UserController
  extends BaseController
{
  @Resource(name="userService")
  private UserServiceImpl userService;
  
  @RequestMapping(value={"/users"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String list(Model model)
  {
    model.addAttribute(this.userService.list());
    return "user/list";
  }
  
  @RequestMapping(value={"/add"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String add(Model model)
  {
    model.addAttribute(new User());
    return "user/add";
  }
  
  @RequestMapping(value={"/add"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String add(@Validated User user, BindingResult br, @RequestParam("files") MultipartFile[] files, HttpServletRequest request)
    throws IOException
  {
    if (br.hasErrors()) {
      return "user/add";
    }
    String realpath = request.getSession().getServletContext().getRealPath("/resources/image");
    System.out.println(realpath);
    MultipartFile[] arrayOfMultipartFile;
    int j = (arrayOfMultipartFile = files).length;
    for (int i = 0; i < j; i++)
    {
      MultipartFile file = arrayOfMultipartFile[i];
      if (!file.isEmpty())
      {
        File f = new File(realpath + "/" + file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), f);
      }
    }
    this.userService.save(user);
    return "redirect:users";
  }
  
  @RequestMapping(value={"/{username}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String show(@PathVariable String username, Model model)
  {
    model.addAttribute(this.userService.getUserByUserName(username));
    return "user/show";
  }
  
  @RequestMapping(value={"/{username}/udpate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String udpate(@PathVariable String username, Model model)
  {
    model.addAttribute(this.userService.getUserByUserName(username));
    return "user/update";
  }
  
  @RequestMapping(value={"/{username}/udpate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String udpate(@Validated User user, BindingResult br, @PathVariable String username)
  {
    if (br.hasErrors()) {
      return "user/update";
    }
    this.userService.saveOrUpdate(user);
    
    return "redirect:/user/users";
  }
  
  @RequestMapping(value={"/{username}/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String delete(@PathVariable String username)
  {
    this.userService.delete(this.userService.getUserByUserName(username));
    return "redirect:/user/users";
  }
}
