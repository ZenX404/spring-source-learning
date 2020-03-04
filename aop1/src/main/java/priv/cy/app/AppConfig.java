package priv.cy.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("priv")
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AppConfig {
}
