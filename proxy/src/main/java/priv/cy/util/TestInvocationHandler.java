package priv.cy.util;

import priv.cy.test.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 这个类就相当于之前我们自己手写模拟jdk动态代理类ProxyUtil，也是传入目标对象，然后进行动态代理。
 */
public class TestInvocationHandler implements InvocationHandler {
    // 用于存储目标对象，这是因为如果想要执行目标对象的方法，在invoke方法中之传入一个方法得对象是没有意义的，必须还需要制定这个方法所在的对象，因为方法执行是依赖于对象的，方法执行的结果是需要作用在对象中的，使用反射机制直接通过方法对象来执行方法必须要指定一个对象
    Object target;

    // jdk的动态代理是通过构造方法将目标对象注入，因为InvocationHandler接口的invoke方法中没有目标对象的参数
    public TestInvocationHandler(Object target) {
        this.target = target;
    }
    /**
     * 这个方法中就是增强代码逻辑
     * @param proxy 代理对象
     * @param method 目标对象中的方法（目标方法）
     * @param args 目标方法的参数
     * @return 增强后代码的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 增强代码逻辑  proxy logic execute
        System.out.println("InvocationHandler：jdk");

        // 执行目标对象的方法，这个invoke方法是学习反射的时候方法对象自带的invoke方法，用于执行方法   target logic execute
        return method.invoke(target, args);
    }
}
