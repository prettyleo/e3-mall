package pojo;

import cn.e3mall.entity.model.TbItem;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * FileName: Item
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/4
 */
@Data
public class Item extends TbItem {

    private String[] images;

    public void setImages(String image) {
        if (!StringUtils.isEmpty(image)) {
            images =  image.split(",");
        }
    }

}
