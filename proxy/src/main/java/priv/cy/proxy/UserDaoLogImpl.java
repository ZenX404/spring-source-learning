package priv.cy.proxy;

import priv.cy.dao.UserDaoImpl;

public class UserDaoLogImpl extends UserDaoImpl {
    @Override
    public void query() {
        System.out.println("log");
        super.query();
    }
}
