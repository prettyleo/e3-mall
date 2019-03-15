package freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import pojo.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * FileName: FreeMarkTest
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/5
 */
public class FreeMarkTest {

    @Test
    public void helloFreeMark() throws IOException, TemplateException {
//        第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());

//        第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("D:\\software_IT\\workspace\\e3-parent\\e3-item-web\\src\\main\\webapp\\WEB-INF\\freemark"));

//        第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setEncoding(Locale.getDefault(), "UTF-8");

//        第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("table.ftl");

//        第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        Map map = new HashMap(16);
        map.put("name", "Leo");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Leo", "id_123", "二年级1班", 88.5));
        studentList.add(new Student("Horbin", "id_456", "二年级2班", 89.5));
        studentList.add(new Student("Jacky", "id_789", "二年级3班", 90.5));
        studentList.add(new Student("Lily", "id_112", "二年级4班", 91.5));
        studentList.add(new Student("Cyan", "id_113", "二年级5班", 99.5));
        map.put("studentList", studentList);
        map.put("date", new Date());
        map.put("empty", null);

//        第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer writer = new FileWriter("D:/table1.html");

//        第七步：调用模板对象的process方法输出文件。
        template.process(map, writer);

//        第八步：关闭流。
        writer.close();

    }

}
