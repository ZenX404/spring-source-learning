package priv.cy.test;

import org.spring.util.BeanFactory;
import priv.cy.service.UserService;
import priv.cy.service.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory("spring.xml");

        UserService service = (UserService) beanFactory.getBean("service");
        service.find();
    }
}
