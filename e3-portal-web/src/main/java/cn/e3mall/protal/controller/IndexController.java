package cn.e3mall.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FileName: IndexController
 * DESCRIPTION: 首页展示
 *
 * @author: SLY
 * @create: 2019/1/19
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}
