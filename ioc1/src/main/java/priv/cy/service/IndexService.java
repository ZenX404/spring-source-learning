package priv.cy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import priv.cy.dao.IndexDao;

@Service
public class IndexService {
    @Autowired
    private IndexDao indexDao;
}
