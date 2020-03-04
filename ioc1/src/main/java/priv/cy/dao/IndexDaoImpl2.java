package priv.cy.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@Profile("dao2")
public class IndexDaoImpl2 implements IndexDao {
    public IndexDaoImpl2() {
        System.out.println("Constructor 2");
    }

    @PostConstruct
    public void init() {
        System.out.println("init 2");
    }

    public void destory() {
        System.out.println("destory 2");
    }
}
