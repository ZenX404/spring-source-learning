package priv.cy.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        /**
         * XML释放到resource包下的，这样编译后才会被复制到classpath路径下，XML的编译就是原样复制，.java的编译时编程.class再放到classpath目录中
         */

//        // 如果使用了XML，就需要用这个类来获取Spring容器，通过该对象来获取bean
//        ClassPathXmlApplicationContext classPathXmlApplicationContext
//                = new ClassPathXmlApplicationContext("classpath:spring.xml");
//
//        IndexService service = (IndexService) classPathXmlApplicationContext.getBean("service");
//        service.service();

        //如果没有使用XML，使用的Javaconfig的方式来进行管理spring的，就不能使用上面的方法来获取Spring容器了，要使用这个类来获取
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(Spring.class);

        IndexService service = (IndexService) annotationConfigApplicationContext.getBean("indexService");
        IndexService service1 = (IndexService) annotationConfigApplicationContext.getBean("indexService");
        IndexService service2 = (IndexService) annotationConfigApplicationContext.getBean("indexService");
        service.service();
        service1.service();
        service2.service();
        /**
         * 也可以使用混合模式，即用Javaconfig也用XML，不用注解将indexDaoImpl装载到spring容器了，而是直接通过XML来用bean标签来装载
         * 然后再Javaconfig那个类上再加一个注解@ImportResource("classpath:spring.xml"),表示也根据xml的配置来管理spring容器，进行混合使用
         */
    }
}
