package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.app.AppConfig;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        annotationConfigApplicationContext.start();

    }
}
