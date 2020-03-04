package priv.cy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope("singleton")
public class IndexService {
//    @Autowired
    private IndexDao dao;

    public void service() {
        System.out.println("service" + this.hashCode());
        System.out.println("dao" + getDao().hashCode());
    }

    @Lookup("indexDaoImpl")
    public IndexDao getDao() {
        return null;
    }
}
