package priv.cy.test;

// 这里引用的是JUL包下的Logger
import java.util.logging.Logger;

/**
 * 使用JUL记录日志不需要引用外部包，因为JUL事jdk自带的
 *
 * 不使用配置文件也可以使用
 */
public class JUL {
    public static void main(String[] args) {
        // JUL的Logger对象只能为其设置字符串命名，不能使用Class作为其标识
        Logger logger = Logger.getLogger("JUL");
        logger.info("jul");
    }
}
