package priv.cy.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDaoImpl1 implements IndexDao {

    @Override
    public void test() {
        System.out.println("Impl 1");
    }


}
