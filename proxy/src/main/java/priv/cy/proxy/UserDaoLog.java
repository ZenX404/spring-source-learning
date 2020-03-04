package priv.cy.proxy;

import priv.cy.dao.UserDao;

/**
 * 代理对象
 * 其实聚合就是一个装饰者模式
 */
public class UserDaoLog implements UserDao {
    // 目标对象
    private UserDao dao;

    // 将目标对象传入这个代理对象，可以使用setter方法也可以使用构造方法，这里建议构造方法
    public UserDaoLog(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void query() {
        System.out.println("log");
        dao.query();
    }

    @Override
    public void query(String str) {
        System.out.println("log");
        dao.query();
    }
}
