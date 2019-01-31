package cn.e3mall.search.controller;

import cn.e3mall.service.SearchService;
import cn.e3mall.vo.resp.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * FileName: SearchController
 * DESCRIPTION: 商品搜索solr
 *
 * @author: SLY
 * @create: 2019/1/30
 */
@Controller
public class SearchController {

    @Value("${rows}")
    private int rows;

    @Autowired
    private SearchService searchService;

    /**
     * @Description: 查询商品
     * @param:  page 查询第几页?
     * @param:  keyword 查询关键字
     * @param:  map 传递给页面的ModelMap
     * @Return: java.lang.String
     * @Author: SLY
     * @Date:   2019/1/30 22:35
     */
    @RequestMapping("/search")
    public String searchItemList(@RequestParam(defaultValue = "1") int page, String keyword, ModelMap map) throws UnsupportedEncodingException {
        // 处理乱码
        keyword = new String(keyword.getBytes("iso-8859-1"), "UTF-8");
        // 查询商品
        SearchResult searchResult = searchService.solrSearch(page, rows, keyword);

        // 把结果传递给页面
        map.addAttribute("query", keyword);
        map.addAttribute("totalPages", searchResult.getTotalPages());
        map.addAttribute("page", page);
        map.addAttribute("recordCount", searchResult.getRecordCount());
        map.addAttribute("itemList", searchResult.getItemList());

        // 返回逻辑视图
        return "search";
    }

}
