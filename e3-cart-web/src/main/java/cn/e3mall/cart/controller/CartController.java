package cn.e3mall.cart.controller;

import cn.e3mall.entity.model.TbItem;
import cn.e3mall.service.ItemService;
import cn.e3mall.util.CookieUtils;
import cn.e3mall.util.E3Result;
import cn.e3mall.util.FastDFSClient;
import com.alibaba.fastjson.JSON;
import lombok.ConfigurationKeys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CartController
 * DESCRIPTION: 购物车controller
 *
 * @author: SLY
 * @create: 2019/2/8
 */
@Slf4j
@Controller
public class CartController {

    private static final String CART = "cart";
    @Autowired
    private ItemService itemService;
    @Value("${CART_COOKIE_EXPIRE}")
    private Integer cartCookieExpire;

    /**
     * @Description: 用户未登陆时加入购物车
     * @param:  itemId 商品id
     * @param:  num 商品数量
     * @param:  request
     * @Return: java.lang.String
     * @Author: SLY
     * @Date:   2019/2/8 23:17
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
                          HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中获取cart列表
        List<TbItem> itemList = getCartListFromCookie(request);

        // 定义一个标志, false-cookie中存在此商品/true-cookie中不存在此商品
        boolean notExist = true;

        // 遍历购物车列表查询是否已有该添加的商品, 若有数量增加, 若无则查询数据库获取商品信息
        for (TbItem tbItem : itemList) {
            if (tbItem.getId().equals(itemId)) {
                tbItem.setNum(tbItem.getNum() + num);
                notExist = false;
                break;
            }
        }
        if (notExist) {
            TbItem item = itemService.getItemById(itemId);
            item.setNum(num);
            // 只要一张图片
            if (StringUtils.isNotBlank(item.getImage())) {
                item.setImage(item.getImage().split(",")[0]);
            }
            itemList.add(item);
        }

        // 加入购物车后, 存入cookie
        CookieUtils.setCookie(request, response, CART, JSON.toJSONString(itemList), cartCookieExpire, true);

        // 响应逻辑视图
        return "cartSuccess";
    }

    /**
     * @Description: 展示购物车列表
     * @param:  request
     * @Return: java.lang.String
     * @Author: SLY
     * @Date:   2019/2/9 0:06
     */
    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request) {
        // 从cookie中获取购物车列表
        List<TbItem> itemList = getCartListFromCookie(request);

        // 将值传递给页面
        request.setAttribute("cartList", itemList);

        // 响应逻辑视图
        return "cart";
    }

    @RequestMapping(value = "/cart/update/num/{itemId}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
                                  HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中获取购物车列表
        List<TbItem> itemList = getCartListFromCookie(request);

        // 更新数量
        for (TbItem tbItem : itemList) {
            if (tbItem.getId().equals(itemId)) {
                tbItem.setNum(num);
                break;
            }
        }

        // 更新cookie
        CookieUtils.setCookie(request, response, CART, JSON.toJSONString(itemList), cartCookieExpire, true);

        // 响应
        return E3Result.ok();
    }

    /**
     * @Description: 从cookie中获取购物车列表
     * @param:  request
     * @Return: java.util.List<cn.e3mall.entity.model.TbItem>
     * @Author: SLY
     * @Date:   2019/2/8 23:27
     */
    private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, CART, true);
        if (StringUtils.isBlank(cookieValue)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(cookieValue, TbItem.class);
    }

}
