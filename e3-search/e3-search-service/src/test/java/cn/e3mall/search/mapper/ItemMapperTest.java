package cn.e3mall.search.mapper;

import cn.e3mall.vo.resp.SearchItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ItemMapperTest {

    @Test
    public void getItemList() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        ItemMapper itemMapper = (ItemMapper) applicationContext.getBean("itemMapper");
        List<SearchItem> itemList = itemMapper.getItemList();
        System.out.println(itemList);

    }
}