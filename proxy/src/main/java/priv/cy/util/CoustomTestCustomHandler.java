package priv.cy.util;

import priv.cy.dao.CoustomInvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义模拟jdk的InvocationHandler接口的实现类
 * 用来自定义代理逻辑
 */
public class CoustomTestCustomHandler  implements CoustomInvocationHandler {
    // 目标对象
    Object target;

    // 通过构造方法注入目标对象
    public CoustomTestCustomHandler(Object target){
        this.target=target;
    }

    /**
     * 代理逻辑（增加代码内容）
     * @param method
     * @return
     */
    @Override
    public Object invoke(Method method) {
        try {
            // 增强代码内容
            System.out.println("coustom jdkProxy");
            // 调用目标对象的原方法
            return  method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
