package priv.cy.test;

import priv.cy.entity.CityEntity;
import priv.cy.util.CommUtil;

public class Test {
    public static void main(String[] args) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId("1");
        cityEntity.setName("test");
        String sql = CommUtil.buildQuerySqlForEntity(cityEntity);

        System.out.println(sql);
    }
}
