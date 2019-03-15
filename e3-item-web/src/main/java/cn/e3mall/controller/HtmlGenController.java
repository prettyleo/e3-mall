package cn.e3mall.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: HtmlGenController
 * DESCRIPTION: 生成静态页面控制器
 *
 * @author: SLY
 * @create: 2019/2/6
 */
@Controller
public class HtmlGenController {

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @RequestMapping("/genHtml")
    @ResponseBody
    public String generateHtml() throws IOException, TemplateException {
        // 1、从spring容器中获得FreeMarkerConfigurer对象。
        // 2、从FreeMarkerConfigurer对象中获得Configuration对象。
        Configuration configuration = freeMarkerConfig.getConfiguration();
        // 3、使用Configuration对象获得Template对象。
        Template template = configuration.getTemplate("hell.ftl");
        // 4、创建数据集
        Map dataModel = new HashMap<>();
        dataModel.put("hello", "1000");
        // 5、创建输出文件的Writer对象。
        Writer out = new FileWriter(new File("D:/spring-freemarker.html"));
        // 6、调用模板对象的process方法，生成文件。
        template.process(dataModel, out);
        // 7、关闭流。
        out.close();
        return "OK";
    }

}
