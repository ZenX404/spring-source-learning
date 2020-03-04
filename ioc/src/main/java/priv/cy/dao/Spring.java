package priv.cy.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 前面讲的是XML和注解的管理spring的方式，可是注解的方式本身还需要通过XML来开启注解支持，所以本质还是离不开XML
 * 所以就可以使用Javaconfig的方式，直接创建一个Java类来管理spring，完全不需要XML
 */
//将该类设置为一个配置类，该类就相当于一个XML
@Configuration
//开启扫描，指定要扫描哪些包，就和XML中开启扫描的作用是一样的，如果不开启就没有办法使用注解
@ComponentScan("priv")
public class Spring {
}
