package priv.cy.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class IndexDaoImpl implements IndexDao {

    @Override
    public void test() {
        System.out.println("Impl 0");
    }


}
