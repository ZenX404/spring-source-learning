package priv.cy.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import priv.cy.config.Appconfig;
import priv.cy.dao.IndexDao;
import priv.cy.dao.IndexDaoImpl1;
import priv.cy.service.IndexService;

public class Test {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext
//                = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 创建AnnotationConfigApplicationContext类的时候先不指定配置类
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        // 获取annotationConfigApplicationContext对象的环境并对其进行修改
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles("dao2");
        // 指定完环境之后再注册配置类
        annotationConfigApplicationContext.register(Appconfig.class);
        // 注册完成后刷新annotationConfigApplicationContext
        annotationConfigApplicationContext.refresh();

        // 再取bean就是在dao2这个环境下获取了
        annotationConfigApplicationContext.getBean(IndexService.class);
    }
}
