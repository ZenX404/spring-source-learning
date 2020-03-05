package priv.cy.test;

import priv.cy.dao.UserDao;
import priv.cy.dao.UserDaoImpl;
import priv.cy.proxy.JdkProxyUtil;
import priv.cy.proxy.ProxyUtil;
import priv.cy.proxy.UserDaoLog;
import priv.cy.proxy.UserDaoLogImpl;
import priv.cy.util.CoustomTestCustomHandler;
import priv.cy.util.TestInvocationHandler;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) throws Exception{
        UserDaoImpl dao = new UserDaoImpl();
        dao.query();
        System.out.println("==============");

        // 使用继承完成代理
        UserDaoImpl daoLog = new UserDaoLogImpl();
        daoLog.query();
        System.out.println("==============");

        // 使用聚合完成代理
        UserDao target = new UserDaoImpl();
        UserDao proxy = new UserDaoLog(target);
        proxy.query();
        System.out.println("==============");

        // 实现动态代理(不能自定义代理逻辑)
        UserDao proxy1 = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
        proxy1.query("动态代理");
        System.out.println("==============");


        // 使用jdk来实现动态代理
        /**
         * 1、传入的参数依次是当前类的类加载器:之前自己手写的那个动态代理实现因为产生的.class并不在项目路径中(classpath)，所以使用的类加载器是第三方类加载器urlClassLoader，
         *                               但是jdk动态代理产生的class就是在项目路径中了，直接使用当前类加载器就可以了。
         * 2、要代理的目标对象实现的所有接口(数组)：我们需要通过目标对象的接口来获得目标对象中的方法
         * 3、InvocationHandler接口的实现类：这个类用于实现增强代码逻辑，需要向这个类中传入需要进行代理的目标对象
         *
         * 总之下面这对代码的意思就是告诉jdk我要代理哪些接口（new Class[]{UserDao.class}）,我要进行代理的逻辑以及目标对象(前面传入接口的实现类)是什么（new TestInvocationHandler(new UserDaoImpl()))）
         * 告知了这些之后jdk就会将我们指定的接口下面的所有方法去实现我们指定的代理逻辑，生成的class通过指定的当前类加载器将其加载入JVM执行
         *
         * proxy logic execute
         * target logic execute
         * */
        UserDao jdkProxy = (UserDao) Proxy.newProxyInstance(Test.class.getClassLoader(),
                new Class[]{UserDao.class}, new TestInvocationHandler(new UserDaoImpl()));

        jdkProxy.query();
        jdkProxy.query("hahah");
        System.out.println("==============");


        // 模拟jdk动态代理（可以实现自定义代理逻辑）
        // 传入JdkProxyUtil的内容的解释和上面jdk源码的解释是一样的
        UserDao coustomJdkProxy = (UserDao) JdkProxyUtil.newInstance(UserDao.class, new CoustomTestCustomHandler(new UserDaoImpl()));
        coustomJdkProxy.query();
    }
}
