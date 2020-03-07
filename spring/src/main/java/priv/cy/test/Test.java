package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.config.AppConfig;
import priv.cy.dao.DaoFactoryBean;
import priv.cy.dao.TempDaoFactoryBean;
import priv.cy.service.IndexService;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();

        // 实现类的单独注册，不需要扫描注解就可以将其来spring容器中实例化
        //annotationConfigApplicationContext.register(AppConfig.class);
        annotationConfigApplicationContext.register(IndexService.class);
        // 使用register方法来实现类的单独注册必须要refresh
        annotationConfigApplicationContext.refresh();

        System.out.println(annotationConfigApplicationContext.getBean(IndexService.class).getClass().getName());


//        TempDaoFactoryBean tempDaoFactoryBean = (TempDaoFactoryBean) annotationConfigApplicationContext.getBean("daoFactory");
//        System.out.println(tempDaoFactoryBean.getMsg1());
//        System.out.println(tempDaoFactoryBean.getMsg2());
//        System.out.println(tempDaoFactoryBean.getMsg3());


//        TempDaoFactoryBean tempDaoFactoryBean = (TempDaoFactoryBean) annotationConfigApplicationContext.getBean("&daoFactoryBean");
//        tempDaoFactoryBean.test();
    }


}

