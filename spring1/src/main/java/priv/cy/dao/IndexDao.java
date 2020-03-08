package priv.cy.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class IndexDao {
    public IndexDao() {
        System.out.println("构造");
    }

    /**
     * 构造方法在初始化方法之前
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    public void query() {
        System.out.println("query");
    }

}
