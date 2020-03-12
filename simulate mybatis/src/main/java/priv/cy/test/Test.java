package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.app.AppConfig;
import priv.cy.app.MyInvocationHandler;
import priv.cy.dao.UserMapper;
import priv.cy.service.UserService;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {

        // 模拟mybatis第一步  生成mapper对象   使用jdk动态代理来实现将接口转换成实例对象，并且这个对象实现了mapper接口
        UserMapper userMapper =  (UserMapper) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{UserMapper.class}, new MyInvocationHandler());

        userMapper.list();



//        AnnotationConfigApplicationContext annotationConfigApplicationContext =
//                new AnnotationConfigApplicationContext(AppConfig.class);


    }
}
