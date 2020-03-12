package priv.cy.test;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import priv.cy.app.MyInvocationHandler;
import priv.cy.dao.UserMapper;

import java.lang.reflect.Proxy;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        /**
         * 1、得到bd
         */


        // 扫描所有的接口   类.class   对象.getClass()
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserMapper.class);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();


        registry.registerBeanDefinition("UserMapper", beanDefinition);
    }
}
