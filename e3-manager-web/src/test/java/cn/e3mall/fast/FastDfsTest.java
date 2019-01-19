package cn.e3mall.fast;

import cn.e3mall.util.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * FileName: FastDfsTest
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/1/16
 */
public class FastDfsTest {

    @Test
    public void testUpload() throws Exception {
        // 创建一个配置文件, 内容是tracker服务器地址


        // 使用全局对象加载配置文件
        ClientGlobal.init("D:/software_IT/workspace/e3-parent/e3-manager-web/src/main/resources/config/client.conf");

        // 创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();

        // 通过TrackClient获得一个TrackerServer
        TrackerServer trackerServer = trackerClient.getConnection();

        // 创建一个StorageServer的引用, 可以是null
        StorageServer storageServer = null;

        // 创建一个StorageClient, 参数需要TrackerServer和StorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);

        // 使用StorageClient上传文件
        String[] jpgs = storageClient.upload_file("D:/software_commons/baiduwangpan/BaiduNetdiskDownload/img/2018-10-18-090345.jpg", "jpg", null);
        System.out.println(Arrays.toString(jpgs));

    }

    @Test
    public void uploadFileTest() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("D:/software_IT/workspace/e3-parent/e3-manager-web/src/main/resources/config/client.conf");
        String s = fastDFSClient.uploadFile("D:/software_commons/baiduwangpan/BaiduNetdiskDownload/img/2018-10-18-090345.jpg");
        System.out.println(s);
    }

}
