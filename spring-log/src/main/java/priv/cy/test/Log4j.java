package priv.cy.test;

// 引用的是log4j的Logger
import org.apache.log4j.Logger;

public class Log4j {
    public static void main(String[] args) {
        // 获取log4j的Logger对象，后面的括号是设置日志对象的唯一标识，可以自定义命名或当前类的Class
        Logger logger = Logger.getLogger(Log4j.class);
        logger.info("log4j");
    }
}
