package priv.cy.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

// 将其装配到spring容器中我们就可以查收bean的实例化过程
@Component
public class TestBeanPostProcessor implements BeanPostProcessor , PriorityOrdered {
    /**
     *
     * BeanPostProcessor是spring框架提供的一个扩展点接口（不止一个），这个是bean的后置器，还有springbean工厂的后置器BeanFactoryPostProcessor
     * 通过实现BeanPostProcessor接口程序员可以插手bean的实例化过程，从而减轻了beanFactory的负担
     *
     * 一个项目可以添加多个BeanPostProcessor
     *
     * 通过下面这两个方法就可以实现AOP动态代理
     *
     * 这两个都是返回的Object
     *
     */

    /**
     * 在初始化之前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("indexDao".equals(beanName)) {
            System.out.println("BeforeInitialization");
        }

        // 插手了bean的实例化过程，这里可以返回原bean，也可以返回代理bean，所以可以用它实现AOP动态代理，在这个方法中调用JDK动态代理生成代理对象
        return bean;
    }

    /**
     * 在初始化之后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("indexDao".equals(beanName)) {
            System.out.println("AfterInitialization");
        }
        return bean;
    }

    /**
     * 通过实现PriorityOrdered这个接口可以设置执行的优先顺序
     * 值越小越优先
     *
     * 如果我们实现了很多BeanPostProcessor这个接口，他会全部执行，那么我们可以通过这个方法来设置优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 200;
    }
}
