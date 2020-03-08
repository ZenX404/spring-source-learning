package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.config.AppConfig;
import priv.cy.dao.IndexDao;

public class Test {
    public static void main(String[] args) {
        // 使用AnnotationConfigApplicationContext构建spring环境，即通过注解的方式初始化应用程序上下文，把spring所有的前提环境准备好，包或spring相关配置，交给spring容器管理的bean全部实例化等
        // 把spring所有的前提环境准备好
        // 1、准备工厂=DefaultListableBeanFactory
        // 实例化一个reader（Bean定义读取器）和scanner（扫描器）
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(AppConfig.class);

        IndexDao dao = annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.query();

    }

}
