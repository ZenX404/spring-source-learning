package priv.cy.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J {
    public static void main(String[] args) {
        // JCL的日志对象是logger，既可以使用当前类的class作为日志对象标识也可以使用自定义命名
        Logger logger = LoggerFactory.getLogger(SLF4J.class);
        logger.info("slf4j");
    }
}
