package priv.cy.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean由名字可以看出，是以bean结尾的，就说明这是一个bean，是由IOC容器管理的一个bean对象
 *
 * 如果你的类实现了FactoryBean
 * 那么spring容器当中会存储两个对象：一个是getObject()方法返回的对象（TempDaoFactoryBean），还有一个就是当前对象（DaoFactoryBean）
 *
 * getObject()返回的对象（TempDaoFactoryBean）存储在spring容器中给这个对象设置的beanName是当前类指定的对象，也就是@Component("daoFactoryBean")中的daoFactoryBean
 * 当前对象（DaoFactoryBean）在spring容器中设置的beanName是在@Component("")指定name的基础上加一个“&”，这里也就是&daoFactoryBean
 *
 * ClassCastException类型转换异常
 */
@Component("daoFactoryBean")
public class DaoFactoryBean implements FactoryBean {
    // 用来传入配置参数
    private String msg;

    // 使用setter方法将其传入
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void testBean() {
        System.out.println("testBean");
    }

    @Override
    public Object getObject() throws Exception {
        // 在FactoryBean内部创建对象实例
        TempDaoFactoryBean temp = new TempDaoFactoryBean();
        String[] msfArray = msg.split(",");
        temp.setMsg1(msfArray[0]);
        temp.setMsg2(msfArray[1]);
        temp.setMsg3(msfArray[2]);

        return temp;
    }

    @Override
    public Class<?> getObjectType() {
        return TempDaoFactoryBean.class;
    }

    /**
     * 是否是单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
