package priv.cy.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@Profile("dao1")
public class IndexDaoImpl1 implements IndexDao {
    public IndexDaoImpl1() {
        System.out.println("Constructor 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("init 1");
    }

    public void destory() {
        System.out.println("destory 1");
    }
}
