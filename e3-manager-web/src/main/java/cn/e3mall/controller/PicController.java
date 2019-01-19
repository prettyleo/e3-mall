package cn.e3mall.controller;

import cn.e3mall.util.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: PicController
 * DESCRIPTION: 图片上传控制器
 *
 * @author: SLY
 * @create: 2019/1/17
 */
@Slf4j
@Controller
public class PicController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map uploadImage(MultipartFile uploadFile) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 通过工具类拿到上传图片的客户端对象
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/client.conf");

            // 获取文件后缀名
            String originalFilename = uploadFile.getOriginalFilename();
            originalFilename = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            // 上传图片
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), originalFilename);

            // 拼接访问图片的URL
            url = IMAGE_SERVER_URL + url;

            // 响应
            map = new HashMap<>();
            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            log.info("【上传图片】uploadImage失败{}", e);
            map.put("error", 1);
            map.put("message", "上传图片失败");
        }
        return map;
    }

}
