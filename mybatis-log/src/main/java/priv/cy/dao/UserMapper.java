package priv.cy.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * dao和mapper的含义是一样的
 *
 * 这里我们不用xml了,一些简单的sql语句直接用这种简单的方法就可以了
 *
 * 一些复杂的sql语句可以写到XML中
 */
public interface UserMapper {

    @Select("select * from user")
    public List<Map<Integer, String>> list();
}
