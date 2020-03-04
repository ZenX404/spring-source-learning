package priv.cy.util;

import priv.cy.anno.Entity;

public class CommUtil {
    /**
     * 通过一个对象构建一条查询sql
     * @param object
     */
    public static String buildQuerySqlForEntity(Object object) {
        String sql = "";
        Class clazz = object.getClass();

        // 1、判断该对象是否加了@Entity这个注解
        if (clazz.isAnnotationPresent(Entity.class)) {
            // 2、获取注解
            Entity entity = (Entity) clazz.getAnnotation(Entity.class);
            // 3、调用注解中的方法
            String entityName = entity.value();

            System.out.println(entityName);

            sql = "select * from " + entityName;

        }

        return sql;
    }
}
