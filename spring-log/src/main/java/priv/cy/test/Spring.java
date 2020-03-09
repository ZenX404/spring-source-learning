package priv.cy.test;



import org.apache.log4j.Logger;

/**
 * 模拟spring日志
 * spring内置的是JCL
 */
public class Spring {
    public static void main(String[] args) {
//        Log log = LogFactory.getLog(Spring.class);
//        log.info("spring");

        Logger logger = Logger.getLogger(Spring.class);
        logger.info("spring");
    }
}
