package priv.cy.test;

import org.spring.util.AnnotationConfigApplicationContext;
import org.spring.util.BeanFactory;
import priv.cy.service.UserService;
import priv.cy.service.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
//        BeanFactory beanFactory = new BeanFactory("spring2.xml");
//
//        UserService service = (UserService) beanFactory.getBean("service");

        // 使用注解配置spring
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("priv.priv.cy.service");

       // service.find();
    }
}
