package org.spring.util;

import org.spring.annotation.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 实现对注解的扫描，使用注解将bean装配到spring容器
 */
public class AnnotationConfigApplicationContext {

    /**
     * 真正的AnnotationConfigApplicationContext也有scan这个方法，方法原型和作用与我们下面模拟的这个方法作用是一致的。
     * @param basePackage
     */
    public void scan(String basePackage) {
        // 获得classpath路径，通过这个路径获取类的全限定名，这样才可以将bean实例化
        // 下面这个是动态获取classpath路径，因为项目部署到不同的服务器，它的classpath路径是会变化的
        String rootPath = this.getClass().getResource("/").getPath();

        // 将包路径转换成文件路径
        // 将一个.换成\   .和\是一个转义字符
        String basePackagePath = basePackage.replaceAll("\\.", "\\\\");

        // 将classpath路径和传入的包路径拼接起来就得到了要扫描的class文件所在路径
        String path = rootPath + "//" + basePackagePath;
        try {
            // 解决路径中文乱码问题
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        // 获取当前路径的所有文件名  这个是通过递归的方法，将这个路径下面所有的文件名都取出来，不取文件夹名
        String[] names = file.list();
        for (String name : names) {
            name = name.replaceAll(".class", "");
            try {
                // 获取要交给容器的bean的Class对象，用来实例化
               Class clazz =  Class.forName(basePackage + "." + name);

               // 如果是模拟@Autowired使用byType的话，就利用反射通过clazz将他的所有的属性field对象取出，然后通过field获取属性的Type，查看spring容器中有没有符合条件的bean，有的话就取出注入，基本过程和模拟xml的一样

               // 判断该类上的注解类型，这里只演示@Service
                if (clazz.isAnnotationPresent(Service.class)) {
                    // 获取注解的对象
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    // 取得注解中的value，用来给bean设置name
                    System.out.println(service.value());
                    // 后面就是将bean实例化放入factory中去，前面对xml方式的模拟已经有了，这里就不再写了，直接实例化输出验证
                    System.out.println(clazz.newInstance());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
