package priv.cy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.cy.dao.UserMapper;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    // 因为这里是byType注入，他就会将我们对UserMapper接口的代理对象注入，代理对象是mybatis根据我们的@Select注解自己生成的
    @Autowired
    private UserMapper userMapper;

    public List<Map<Integer, String>> list() {
        // 如果想在mybatis记录日志，就要在调用mapper之前调用这个方法（不同的日志包对象不同的方法，这里我们用的是log4j）
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        return userMapper.list();
    }

}
