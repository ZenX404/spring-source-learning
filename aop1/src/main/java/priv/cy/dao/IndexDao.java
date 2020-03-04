package priv.cy.dao;

import org.springframework.stereotype.Repository;
import priv.cy.anno.TAT;

@Repository
public class IndexDao implements Dao{

    @Override
    public void query(String str) {
        System.out.println("query 1");
        System.out.println(str);
    }

    @Override
    public void query() {
        System.out.println("query 2");
    }
}
