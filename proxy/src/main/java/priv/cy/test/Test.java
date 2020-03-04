package priv.cy.test;

import priv.cy.dao.UserDao;
import priv.cy.dao.UserDaoImpl;
import priv.cy.proxy.ProxyUtil;
import priv.cy.proxy.UserDaoLog;
import priv.cy.proxy.UserDaoLogImpl;

public class Test {
    public static void main(String[] args) {
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

        // 实现动态代理
        UserDao proxy1 = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
        proxy1.query("动态代理");
    }
}
