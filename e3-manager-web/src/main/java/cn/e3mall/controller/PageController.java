package cn.e3mall.controller;

import cn.e3mall.service.ItemService;
import cn.e3mall.vo.resp.PageResult;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: PageController
 * DESCRIPTION: 页面跳转controller
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable("page") String page) {
        return page;
    }


}
