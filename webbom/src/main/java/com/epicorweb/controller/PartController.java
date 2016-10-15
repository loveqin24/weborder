package com.epicorweb.controller;

import com.epicorweb.services.PartService;
import com.epicorweb.services.impl.PartServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/part"})
public class PartController
  extends BaseController
{
  @Resource(name="partService")
  private PartServiceImpl partService;
  int userpagesize = 20;

  @RequestMapping(value={"/parts"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String list(String page, Model model)
  {
    int pageSize = this.userpagesize;
    Long totalsize = this.partService.getCount();
    model.addAttribute("totalsize", totalsize);
    int pageTimes;
    if (totalsize.longValue() % pageSize == 0L) {
      pageTimes = (int)(totalsize.longValue() / pageSize);
    } else {
      pageTimes = (int)(totalsize.longValue() / pageSize + 1L);
    }
    model.addAttribute("pageTimes", Integer.valueOf(pageTimes));
    if (page == null|| page.isEmpty()) {
      page = "1";
    }
    int startRow = (Integer.parseInt(page) - 1) * pageSize;
    
    model.addAttribute("currentPage", Integer.valueOf(Integer.parseInt(page)));
    model.addAttribute(this.partService.list(startRow, pageSize));
    
    return "part/list";
  }
}
