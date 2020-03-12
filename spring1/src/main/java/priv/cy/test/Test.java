package priv.cy.test;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringValueResolver;
import priv.cy.config.AppConfig;
import priv.cy.dao.IndexDao;

import java.beans.PropertyEditor;
import java.security.AccessControlContext;

public class Test {
    public static void main(String[] args) {
        // 使用AnnotationConfigApplicationContext构建spring环境，即通过注解的方式初始化应用程序上下文，把spring所有的前提环境准备好，包或spring相关配置，交给spring容器管理的bean全部实例化等
        // 把spring所有的前提环境准备好
        // 1、准备工厂=DefaultListableBeanFactory
        // 实例化一个reader（Bean定义读取器）和scanner（扫描器）
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();

        // 把一个class转换成bd，然后put到map中，这个map就是DefaultListableBeanFactory中的成员属性beanDefinitionMap
        annotationConfigApplicationContext.register(AppConfig.class);
        // 初始化spring环境
        annotationConfigApplicationContext.refresh();
        // 当完成了上面的过程之后，就已经完成了扫描，但是完成这个扫描工作的不是AnnotationConfigApplicationContext里面的scanner(扫描器)，而是在reader的构造方法AnnotatedBeanDefinitionReader中完成的
        // 其实scanner只是供我们外部调用扫描用的，spring源码内部不适用这个scanner来完成扫描


        IndexDao dao = annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.query();


//        ClassPathXmlApplicationContext classPathXmlApplicationContext =
//                new ClassPathXmlApplicationContext();
    }

}

