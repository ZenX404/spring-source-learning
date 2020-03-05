package priv.cy.dao;

import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler接口：模拟jdk的InvocationHandler接口
 */
public interface CoustomInvocationHandler {
    public Object invoke(Method method);
}
