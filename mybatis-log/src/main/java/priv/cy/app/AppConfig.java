package priv.cy.app;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("priv")
// 设置要扫描哪些mapper  用于进行数据库操作
@MapperScan("priv.priv.cy.dao")
public class AppConfig {

    /**
     * 如果直接使用mybatais,我们还需要手动配置各种依赖
     *
     * 但是我们如果使用spring-mybatis来实现spring整合mybatis,我们只需要写下面的这一段配置数据源的代码(配置JDBC连接)，然后将数据源对象交给spring容器就可以了
     *
     * 前提不要忘了引用数据库连接池依赖和mysql依赖
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/speed_kill_system?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false");
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return driverManagerDataSource;
    }

    /**
     * 配置获取sqlSessionFactoryBean，并将其交给spring容器管理
     * @return
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }
}
