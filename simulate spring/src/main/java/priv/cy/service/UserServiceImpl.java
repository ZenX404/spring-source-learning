package priv.cy.service;

import priv.cy.dao.UserDao;

public class UserServiceImpl implements UserService {
    UserDao dao;

    /**
     * service是依赖dao这个对象的，我们已经将dao装配到spring容器之后，就需要由spring容器帮我们将dao注入到service对象中
     * 要实现这个注入过程，需要我们提供给spring容器一个注入接口，要通过这个接口将创建好的dao对象注入到service对象中
     * 接口有两种：
     * 1、setter方法
     * 2、构造方法
     *
     * spring容器会使用你提供的setter方法或者是构造方法来将dao对象注入进来
     *
     * 使用哪种方法可以用XML标签来指定：
     * ·使用构造方法必须将带有dao参数的构造方法编写出来
     * ·使用setter方法也需要编写好dao的setter方法
     */

    /**
     * 有参构造方法
     * 因为要利用反射创建service对象，所以还需要一个无参构造方法
     * @param dao
     */
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    /**
     * 无参构造方法用于反射创建service对象
     */
    public UserServiceImpl() {}

    /**
     * setter方法
     * @param dao
     */
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void find() {
        System.out.println("service");
        dao.query();
    }
}
