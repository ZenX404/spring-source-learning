package priv.cy.dao;

public class UserDaoImpl implements UserDao {

    @Override
    public void query() throws Exception{
        System.out.println("假装查询数据库");
    }

    @Override
    public void query(String str)  throws Exception{
        System.out.println(str);
        System.out.println("假装查询数据库");
    }
}
