package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.app.AppConfig;
import priv.cy.service.UserService;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(annotationConfigApplicationContext.getBean(UserService.class).list());
    }
}
